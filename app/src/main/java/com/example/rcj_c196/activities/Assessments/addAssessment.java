package com.example.rcj_c196.activities.Assessments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rcj_c196.Database.Repository;
import com.example.rcj_c196.R;
import com.example.rcj_c196.activities.Terms.TermList;
import com.example.rcj_c196.activities.Terms.detailTerm;
import com.example.rcj_c196.entities.Assessments;

public class addAssessment extends AppCompatActivity {

    EditText addName;
    EditText addStart;
    EditText addEnd;
    EditText addType;

    int courseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

        addName = findViewById(R.id.assessmentTitle);
        addType = findViewById(R.id.assessmentType);
        addStart = findViewById(R.id.assessmentStart);
        addEnd = findViewById(R.id.assessmentEnd);
        courseID = getIntent().getIntExtra("Course ID", -1);

        Button button = findViewById(R.id.saveAssessment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Assessments a = new Assessments(0, addName.getText().toString(), addType.getText().toString(), addStart.getText().toString(), addEnd.getText().toString(), courseID);
                Repository repository = new Repository(getApplication());
                repository.insert(a);
                Intent intent4 = new Intent(addAssessment.this, TermList.class);
                startActivity(intent4);
            }
        });
    }
}