package com.example.rcj_c196.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rcj_c196.entities.Terms;

import java.util.List;

@Dao
public interface TermsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Terms terms);

    @Update
    void update(Terms terms);

    @Delete
    void delete(Terms terms);

    @Query("Select * FROM TERMSTABLE ORDER BY termID ASC")
    List<Terms> getallTerms();

}
