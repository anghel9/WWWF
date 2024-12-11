package com.example.groupproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.groupproject.database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " ORDER BY username")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE username = :username")
    LiveData<User> getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE id = :userId")
    LiveData<User> getUserById(int userId);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE username = :username AND password = :password")
    User authenticateUser(String username, String password);

    @Query("SELECT COUNT(*) FROM " + AppDatabase.USER_TABLE)
    int countUsers();

    @Query("SELECT COUNT(*) FROM " + AppDatabase.USER_TABLE + " WHERE username = :username")
    int isUsernameTaken(String username);

    @Query("UPDATE " + AppDatabase.USER_TABLE + " SET currentCreatureId = :creatureId WHERE id = :userId")
    void updateAssignedCreature(int creatureId, int userId);

    @Query("DELETE FROM " + AppDatabase.USER_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE currentCreatureId = :creatureId")
    LiveData<User> getUserByCreatureId(int creatureId);


}
