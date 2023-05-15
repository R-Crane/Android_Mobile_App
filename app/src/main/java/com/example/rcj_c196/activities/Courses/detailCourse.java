package com.example.rcj_c196.activities.Courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import com.example.rcj_c196.activities.Assessments.addAssessment;
import com.example.rcj_c196.activities.MainActivity;
import com.example.rcj_c196.activities.Terms.TermList;
import com.example.rcj_c196.adapter.AssessmentAdapter;
import com.example.rcj_c196.entities.Assessments;
import com.example.rcj_c196.entities.Courses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class detailCourse extends AppCompatActivity {

    int numAssess;
    Courses currentCourse;
    Courses course;
    int courseID;
    String name;
    String start;
    String end;
    String status;
    String insName;
    String insPhone;
    String insEmail;
    int termID;

    EditText editCourseName;
    EditText editCourseStart;
    EditText editCourseEnd;
    EditText editCourseStatus;
    EditText editCourseInsName;
    EditText editCourseInsPhone;
    EditText editCourseInsEmail;
    EditText note;
    EditText notifyDate;
    DatePickerDialog.OnDateSetListener startEndDate;
    final Calendar calDate = Calendar.getInstance();

    private Repository repository;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        courseID = getIntent().getIntExtra("ID", -1);
        termID = getIntent().getIntExtra("Term ID", 0);

        note = findViewById(R.id.noteTExt);

        name = getIntent().getStringExtra("Name");
        editCourseName = findViewById(R.id.editTextTextPersonName10);
        editCourseName.setText(name);

        start = getIntent().getStringExtra("Course Start");
        editCourseStart = findViewById(R.id.editTextTextPersonName11);
        editCourseStart.setText(start);

        end = getIntent().getStringExtra("Course End");
        editCourseEnd = findViewById(R.id.editTextTextPersonName12);
        editCourseEnd.setText(end);

        status = getIntent().getStringExtra("Course Status");
        editCourseStatus = findViewById(R.id.editTextTextPersonName13);
        editCourseStatus.setText(status);

        insName = getIntent().getStringExtra("Instructor Name");
        editCourseInsName = findViewById(R.id.editTextTextPersonName14);
        editCourseInsName.setText(insName);

        insPhone = getIntent().getStringExtra("Instructor Phone");
        editCourseInsPhone = findViewById(R.id.editTextTextPersonName15);
        editCourseInsPhone.setText(insPhone);

        insEmail = getIntent().getStringExtra("Instructor Email");
        editCourseInsEmail = findViewById(R.id.editTextTextPersonName16);
        editCourseInsEmail.setText(insEmail);

        notifyDate = findViewById(R.id.editTextDate4);

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        repository = new Repository(getApplication());
        List<Assessments> allAssessments = repository.getAllAssessments();
        List<Assessments> filterAssessment = new ArrayList<>();
        for (Assessments a:allAssessments
        ) {if (a.getCourseID()==courseID)
            filterAssessment.add(a);
        }

        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(filterAssessment);

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
                new DatePickerDialog(detailCourse.this, startEndDate, calDate
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
        getMenuInflater().inflate(R.menu.menu_zcourse, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addAssessmentC:
                Intent intent1 = new Intent(detailCourse.this, addAssessment.class);
                intent1.putExtra("Course ID", courseID);
                startActivity(intent1);
                return true;
            case R.id.saveChangeCourse:
                course = new Courses(courseID, editCourseName.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), editCourseStatus.getText().toString(), editCourseInsName.getText().toString(), editCourseInsPhone.getText().toString(), editCourseInsEmail.getText().toString(), termID);
                repository.update(course);
                Intent intent2 = new Intent(detailCourse.this, TermList.class);
                startActivity(intent2);
                return true;
            case R.id.shareNOte:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, note.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE,"Name Of Message");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notify:
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
                Intent intent= new Intent(detailCourse.this,MyReceiver.class);
                intent.putExtra("key","Show this Message - Alert Created");
                PendingIntent sender=PendingIntent.getBroadcast(detailCourse.this, ++MainActivity.numAlert,intent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
                //Logic to prevent delete of course that has assessments tied to it
            case R.id.deleteC:
                for (Courses courses: repository.getAllCourses()){
                    if (courses.getCourseID() == courseID) currentCourse = courses;
                }

                numAssess = 0;
                for (Assessments assessments: repository.getAllAssessments()) {
                    if(assessments.getCourseID() == courseID) ++numAssess;
                }

                if (numAssess == 0){
                    repository.delete(currentCourse);
                    Toast.makeText(detailCourse.this, currentCourse.getCourseTitle() + " was Deleted!", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(detailCourse.this, TermList.class);
                    startActivity(intent3);
                }
                else {
                    Toast.makeText(detailCourse.this, "Cant delete if assessments associated!", Toast.LENGTH_SHORT).show();
                    Intent intent4 = new Intent(detailCourse.this, TermList.class);
                    startActivity(intent4);
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        repository = new Repository(getApplication());
        List<Assessments> allAssessments = repository.getAllAssessments();
        List<Assessments> filterAssessment = new ArrayList<>();
        for (Assessments a:allAssessments
        ) {if (a.getCourseID()==courseID)
            filterAssessment.add(a);
        }

        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(filterAssessment);
    }
}