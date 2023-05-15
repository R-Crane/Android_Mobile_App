package com.example.rcj_c196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rcj_c196.entities.Assessments;
import com.example.rcj_c196.entities.Terms;

import java.util.List;

@Dao
public interface AssessmentsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessments assessments);

    @Update
    void update(Assessments assessments);

    @Delete
    void delete(Assessments assessments);

    @Query("Select * FROM ASSESSMENTSTABLE ORDER BY assessmentID ASC")
    List<Assessments> getAllAssessments();


}
