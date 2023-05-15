package com.example.rcj_c196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rcj_c196.entities.Courses;
import com.example.rcj_c196.entities.Terms;

import java.util.List;

@Dao
public interface CoursesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Courses courses);

    @Update
    void update(Courses courses);

    @Delete
    void delete(Courses courses);

    @Query("Select * FROM COURSESTABLE ORDER BY courseID ASC")
    List<Courses> getAllCourses();


}
