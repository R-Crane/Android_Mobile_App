package com.example.rcj_c196.activities.Terms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rcj_c196.Database.Repository;
import com.example.rcj_c196.R;
import com.example.rcj_c196.entities.Terms;

public class addTerm extends AppCompatActivity {

    EditText addName;
    EditText addStart;
    EditText addEnd;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        addName =  findViewById(R.id.addTermName);
        addStart = findViewById(R.id.addTermStart);
        addEnd = findViewById(R.id.addTermEnd);

        Button button = findViewById(R.id.saveTerm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Terms t = new Terms(0, addName.getText().toString(), addStart.getText().toString(),addEnd.getText().toString());
                Repository repository = new Repository(getApplication());
                repository.insert(t);
                Intent intent4 = new Intent(addTerm.this, TermList.class);
                startActivity(intent4);
            }
        });

    }

}