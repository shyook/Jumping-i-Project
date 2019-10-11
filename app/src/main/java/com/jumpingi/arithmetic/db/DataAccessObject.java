package com.jumpingi.arithmetic.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jumpingi.arithmetic.ui.data.QuestionData;

import java.util.Date;
import java.util.List;

@Dao
public interface DataAccessObject {
    @Query("SELECT * FROM QuestionData")
    List<QuestionData> getAllHistory();

    @Query("DELETE FROM QuestionData")
    void clearAll();

    @Query("SELECT * FROM QuestionData WHERE date = :date")
    List<QuestionData> getHistory(Date date);

    @Insert
    void insert(QuestionData data);

    @Update
    void update(QuestionData data);

    @Delete
    void delete(QuestionData data);

}
