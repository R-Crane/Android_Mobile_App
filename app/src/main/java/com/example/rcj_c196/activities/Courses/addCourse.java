package com.example.rcj_c196.activities.Courses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rcj_c196.Database.Repository;
import com.example.rcj_c196.R;
import com.example.rcj_c196.activities.Terms.TermList;
import com.example.rcj_c196.activities.Terms.detailTerm;
import com.example.rcj_c196.entities.Courses;
import com.example.rcj_c196.entities.Terms;
//import com.example.rcj_c196.activities.Terms.editTerm;

public class addCourse extends AppCompatActivity {

    EditText addName;
    EditText addStart;
    EditText addEnd;
    EditText addStatus;
    EditText addInsName;
    EditText addInsPhone;
    EditText addInsEmail;
    int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        addName = findViewById(R.id.courseAddName);
        addStart = findViewById(R.id.courseAddStart);
        addEnd = findViewById(R.id.courseAddEnd);
        addStatus = findViewById(R.id.courseAddStatus);
        addInsName = findViewById(R.id.courseIntstructorName);
        addInsPhone = findViewById(R.id.courseAddPhone);
        addInsEmail = findViewById(R.id.courseAddEmail);
        termID = getIntent().getIntExtra("Term ID", -1);


        Button button = findViewById(R.id.saveCoursebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Courses c = new Courses(0, addName.getText().toString(), addStart.getText().toString(), addEnd.getText().toString(), addStatus.getText().toString(), addInsName.getText().toString(), addInsPhone.getText().toString(), addInsEmail.getText().toString(), termID );
                Repository repository = new Repository(getApplication());
                repository.insert(c);
                Intent intent4 = new Intent(addCourse.this, TermList.class);
                startActivity(intent4);

            }
        });

    }


}