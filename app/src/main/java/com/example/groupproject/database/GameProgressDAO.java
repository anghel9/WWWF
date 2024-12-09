package com.example.groupproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.groupproject.database.entities.GameProgress;

import java.util.List;

@Dao
public interface GameProgressDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGameProgress(GameProgress gameProgress);

    @Query("SELECT * FROM " + AppDatabase.GAME_PROGRESS_TABLE + " WHERE userId = :userId")
    LiveData<GameProgress> getGameProgressForUser(int userId);

    @Query("SELECT * FROM " + AppDatabase.GAME_PROGRESS_TABLE)
    LiveData<List<GameProgress>> getAllGameProgress();

    @Update
    void updateGameProgress(GameProgress gameProgress);

    @Delete
    void deleteGameProgress(GameProgress gameProgress);
}
