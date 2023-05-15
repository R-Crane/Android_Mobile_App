package com.example.rcj_c196.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcj_c196.R;
import com.example.rcj_c196.activities.Courses.detailCourse;
import com.example.rcj_c196.entities.Courses;
import com.example.rcj_c196.entities.Terms;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {


    class CourseViewHolder extends RecyclerView.ViewHolder {

        private final TextView courseItemView;

        private CourseViewHolder(View itemview) {
            super(itemview);
            courseItemView = itemview.findViewById(R.id.courseListItem);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Courses current=mCourses.get(position);
                    Intent intent = new Intent(context, detailCourse.class);
                    intent.putExtra("ID", current.getCourseID());
                    intent.putExtra("Name", current.getCourseTitle());
                    intent.putExtra("Course Start", current.getCourseStart());
                    intent.putExtra("Course End", current.getCourseEnd());
                    intent.putExtra("Course Status", current.getCourseStatus());
                    intent.putExtra("Instructor Name", current.getInstructorName());
                    intent.putExtra("Instructor Phone", current.getInstructorPhone());
                    intent.putExtra("Instructor Email", current.getInstructorEmail());
                    intent.putExtra("Term ID", current.getTermID());
                    context.startActivity(intent);
                }
            });
        }
    }
        private List<Courses> mCourses;

        private final Context context;

        private final LayoutInflater mInflater;

        public CourseAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_list_item,parent,false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
    if (mCourses!=null){
        Courses current=mCourses.get(position);
        String name=current.getCourseTitle();
        holder.courseItemView.setText(name);
    }
    else {
        holder.courseItemView.setText("Nothing To Show");
    }
    }

    @Override
    public int getItemCount() {return mCourses.size();}

    public void setCourses(List<Courses> courses){
        mCourses=courses;
        notifyDataSetChanged();
    }
}

