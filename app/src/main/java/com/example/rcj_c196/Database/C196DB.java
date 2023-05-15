package com.example.rcj_c196.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rcj_c196.DAO.AssessmentsDAO;
import com.example.rcj_c196.DAO.CoursesDAO;
import com.example.rcj_c196.DAO.TermsDAO;
import com.example.rcj_c196.entities.Assessments;
import com.example.rcj_c196.entities.Courses;
import com.example.rcj_c196.entities.Terms;

@Database(entities = {Terms.class, Courses.class, Assessments.class}, version=1, exportSchema = false)
public abstract class C196DB extends RoomDatabase {

    public abstract TermsDAO termsDAO();
    public abstract CoursesDAO coursesDAO();
    public abstract AssessmentsDAO assessmentsDAO();

    public static volatile C196DB INSTANCE;

    static C196DB getDatabase(final Context context){
        if(INSTANCE==null){
    synchronized (C196DB.class){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),C196DB.class,"wgu196database.db")
                    .fallbackToDestructiveMigration()
                    .build();
                }

            }

        }
        return INSTANCE;
    }

}
