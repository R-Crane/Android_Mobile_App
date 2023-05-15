package com.example.rcj_c196.Database;

import android.app.Application;

import com.example.rcj_c196.DAO.AssessmentsDAO;
import com.example.rcj_c196.DAO.CoursesDAO;
import com.example.rcj_c196.DAO.TermsDAO;
import com.example.rcj_c196.entities.Assessments;
import com.example.rcj_c196.entities.Courses;
import com.example.rcj_c196.entities.Terms;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private TermsDAO mTermsDAO;
    private CoursesDAO mCoursesDAO;
    private AssessmentsDAO mAssessmentsDAO;
    private List<Terms> mAllTerms;
    private List<Courses> mAllCourses;
    private List<Assessments> mAllAssessments;


    private static int NUMBER_OF_THREADS=6;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        C196DB db=C196DB.getDatabase(application);
        mTermsDAO=db.termsDAO();
        mCoursesDAO=db.coursesDAO();
        mAssessmentsDAO=db.assessmentsDAO();
    }
    public List<Terms>getAllTerms() {
        databaseExecutor.execute(()->{
            mAllTerms=mTermsDAO.getallTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllTerms;
    }
    public void insert(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.insert(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.update(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.delete(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Courses>getAllCourses() {
        databaseExecutor.execute(()->{
            mAllCourses=mCoursesDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllCourses;
    }
    public void insert(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.insert(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.update(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.delete(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Assessments>getAllAssessments() {
        databaseExecutor.execute(()->{
            mAllAssessments=mAssessmentsDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllAssessments;
    }
    public void insert(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentsDAO.insert(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentsDAO.update(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentsDAO.delete(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}
