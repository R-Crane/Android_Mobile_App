package com.example.rcj_c196.activities.Assessments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rcj_c196.Database.Repository;
import com.example.rcj_c196.R;
import com.example.rcj_c196.activities.Courses.MyReceiver;
import com.example.rcj_c196.activities.Courses.addCourse;
import com.example.rcj_c196.activities.Courses.detailCourse;
import com.example.rcj_c196.activities.MainActivity;
import com.example.rcj_c196.activities.Terms.TermList;
import com.example.rcj_c196.activities.Terms.detailTerm;
import com.example.rcj_c196.adapter.AssessmentAdapter;
import com.example.rcj_c196.entities.Assessments;
import com.example.rcj_c196.entities.Courses;
import com.example.rcj_c196.entities.Terms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class detailAssessment extends AppCompatActivity {

    Assessments currentAssessment;

    Assessments assessment;
    EditText editName;
    EditText editType;
    EditText editStart;
    EditText editEnd;

    String name;
    String start;
    String end;
    String type;

    int assessmentID;
    int courseID;

    EditText notifyDate;
    DatePickerDialog.OnDateSetListener startEndDate;
    final Calendar calDate = Calendar.getInstance();


    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_assessment);

        assessmentID = getIntent().getIntExtra("ID", -1);
        courseID = getIntent().getIntExtra("Course ID", 0);

        name = getIntent().getStringExtra("Name");
        editName = findViewById(R.id.assessName);
        editName.setText(name);

        type = getIntent().getStringExtra("Assessment Status");
        editType = findViewById(R.id.assessType);
        editType.setText(type);

        start = getIntent().getStringExtra("Assessment Start");
        editStart = findViewById(R.id.assessStart);
        editStart.setText(start);

        end = getIntent().getStringExtra("Assessment End");
        editEnd = findViewById(R.id.assessEnd);
        editEnd.setText(end);

        notifyDate = findViewById(R.id.editTextDate3);

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        repository = new Repository(getApplication());

        startEndDate = new DatePickerDialog.OnDateSetListener() {

            //assistance from Caryolyn's Video series and Instructor - help with creating alerts to be set

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calDate.set(Calendar.YEAR, year);
                calDate.set(Calendar.MONTH, monthOfYear);
                calDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }

        };

        //assistance from Caryolyn's Video series and Instructor - help with creating alerts to be set
        notifyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info=notifyDate.getText().toString();
                try{
                    calDate.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(detailAssessment.this, startEndDate, calDate
                        .get(Calendar.YEAR), calDate.get(Calendar.MONTH),
                        calDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        notifyDate.setText(sdf.format(calDate.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessment, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveAssess:
                assessment = new Assessments(assessmentID, editName.getText().toString(), editType.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), courseID);
                repository.update(assessment);
                Intent intent2 = new Intent(detailAssessment.this, TermList.class);
                startActivity(intent2);
                return true;
            case R.id.notifyA:
                String dateFromScreen=notifyDate.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate=sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger=myDate.getTime();
                Intent intent= new Intent(detailAssessment.this, MyReceiver.class);
                intent.putExtra("key","Show this Message - Alert Created");
                PendingIntent sender=PendingIntent.getBroadcast(detailAssessment.this, ++MainActivity.numAlert,intent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.deleteAssess:
                for (Assessments assessments: repository.getAllAssessments()){
                    if (assessments.getAssessmentID() == assessmentID) currentAssessment = assessments;
                }
                    repository.delete(currentAssessment);
                    Toast.makeText(detailAssessment.this, currentAssessment.getAssessmentName() + " was Deleted!", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(detailAssessment.this, TermList.class);
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}