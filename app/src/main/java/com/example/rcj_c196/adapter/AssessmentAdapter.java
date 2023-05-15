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
import com.example.rcj_c196.activities.Assessments.detailAssessment;
import com.example.rcj_c196.activities.Courses.detailCourse;
import com.example.rcj_c196.entities.Assessments;
import com.example.rcj_c196.entities.Courses;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{

    class AssessmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView assessmentItemView;

        private AssessmentViewHolder(View itemview) {
            super(itemview);
            assessmentItemView = itemview.findViewById(R.id.assessmentListItem);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessments current=mAssessments.get(position);
                    Intent intent = new Intent(context, detailAssessment.class);
                    intent.putExtra("ID", current.getAssessmentID());
                    intent.putExtra("Name", current.getAssessmentName());
                    intent.putExtra("Assessment Status", current.getAssessmentType());
                    intent.putExtra("Assessment Start", current.getAssessmentStart());
                    intent.putExtra("Assessment End", current.getAssessmentComplete());
                    intent.putExtra("Course ID", current.getCourseID());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Assessments> mAssessments;

    private final Context context;

    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentAdapter.AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments!=null){
            Assessments current=mAssessments.get(position);
            String name=current.getAssessmentName();
            holder.assessmentItemView.setText(name);
        }
        else {
            holder.assessmentItemView.setText("Nothing To Show");
        }
    }

    @Override
    public int getItemCount() {return mAssessments.size();}
    public void setAssessments(List<Assessments> assessments){
        mAssessments=assessments;
        notifyDataSetChanged();
    }
}
