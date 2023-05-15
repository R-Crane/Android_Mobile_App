package com.example.rcj_c196.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rcj_c196.Database.Repository;
import com.example.rcj_c196.R;
import com.example.rcj_c196.activities.Assessments.AssessmentList;
import com.example.rcj_c196.activities.Courses.CourseList;
import com.example.rcj_c196.activities.Terms.TermList;
import com.example.rcj_c196.entities.Assessments;
import com.example.rcj_c196.entities.Courses;
import com.example.rcj_c196.entities.Terms;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.buttonTerms1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TermList.class);
                startActivity(intent);
            }
        });

//        Button button1 = findViewById(R.id.buttonCourses);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, CourseList.class);
//                startActivity(intent);
//            }
//        });

//        Button button2 = findViewById(R.id.buttonAssessment);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AssessmentList.class);
//                startActivity(intent);
//            }
//        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.addSampleData:
                Terms terms = new Terms(0, "Spring", "01/01/2023", "04/30/2023");
                Repository repository = new Repository(getApplication());
                repository.insert(terms);
//                Courses courses = new Courses(0, "Course 1", "01/01/2023", "04/30/2023", "In Progress", "Teacher", "7025551234", "Teacher@wgu.edu", 1 );
//                repository.insert(courses);
//                Assessments assessments = new Assessments(0, "Test Test", "Objective", "01/01/2023","04/30/2023", 1);
//                repository.insert(assessments);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}