package com.example.rcj_c196.activities.Terms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rcj_c196.Database.Repository;
import com.example.rcj_c196.R;
import com.example.rcj_c196.activities.Courses.addCourse;
import com.example.rcj_c196.activities.MainActivity;
import com.example.rcj_c196.adapter.CourseAdapter;
import com.example.rcj_c196.adapter.TermAdapter;
import com.example.rcj_c196.entities.Courses;
import com.example.rcj_c196.entities.Terms;

import java.util.ArrayList;
import java.util.List;

public class detailTerm extends AppCompatActivity {
    EditText editTermName;
    EditText editTermS;
    EditText editTermE;
    Terms term;
    int termID;
    String name;

    String start;

    String end;

    Terms currentTerm;
    int numCourses;

    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_term);



        name = getIntent().getStringExtra("Name");
        editTermName = findViewById(R.id.editTermName);
        editTermName.setText(name);

        start = getIntent().getStringExtra("Start Date");
        editTermS = findViewById(R.id.editTermStart);
        editTermS.setText(start);

        end = getIntent().getStringExtra("End Date");
        editTermE = findViewById(R.id.editTermEnd);
        editTermE.setText(end);




        termID = getIntent().getIntExtra("ID", -1);



        repository = new Repository(getApplication());
        List<Courses> allCourses = repository.getAllCourses();
        List<Courses> filteredCourses = new ArrayList<>();
        for (Courses c:allCourses
             ) {if (c.getTermID()==termID)
            filteredCourses.add(c);
        }
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(filteredCourses);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addC:
                Intent intent1 = new Intent(detailTerm.this, addCourse.class);
                intent1.putExtra("Term ID", termID);
                startActivity(intent1);
                return true;
            case R.id.saveChangeTerm:
                term = new Terms(termID, editTermName.getText().toString(), editTermS.getText().toString(), editTermE.getText().toString());
                repository.update(term);
                Intent intent2 = new Intent(detailTerm.this, TermList.class);
                startActivity(intent2);
                return true;
                //logic to prevent term from being deleted that has a course tied to it
            case R.id.deleteT:
            for (Terms terms: repository.getAllTerms()){
                if (terms.getTermID() == termID) currentTerm = terms;
            }

            numCourses = 0;
            for (Courses courses: repository.getAllCourses()) {
                if(courses.getTermID() == termID) ++numCourses;
            }

            if (numCourses == 0){
                repository.delete(currentTerm);
                Toast.makeText(detailTerm.this, currentTerm.getTermName() + " was Deleted!", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(detailTerm.this, TermList.class);
                startActivity(intent3);
            }
            else {
                Toast.makeText(detailTerm.this, "Cant delete if courses associated!", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(detailTerm.this, TermList.class);
                startActivity(intent4);
            }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        repository = new Repository(getApplication());
        List<Courses> allCourses = repository.getAllCourses();
        List<Courses> filteredCourses = new ArrayList<>();
        for (Courses c:allCourses
        ) {if (c.getTermID()==termID)
            filteredCourses.add(c);
        }
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(filteredCourses);
    }

}