package com.example.groupproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.groupproject.database.entities.GameProgress;
import com.example.groupproject.database.entities.Inventory;
import com.example.groupproject.database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {

    // User CRUD operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Delete
    void delete(User user);

    @Delete
    void deleteUsers(List<User> users);

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

    @Query("DELETE FROM " + AppDatabase.USER_TABLE)
    void deleteAll();

    // GameProgress CRUD operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGameProgress(GameProgress gameProgress);

    @Transaction
    @Query("SELECT * FROM gameProgressTable WHERE userId = :userId")
    LiveData<GameProgress> getGameProgressForUser(int userId);

    @Delete
    void deleteGameProgress(GameProgress gameProgress);

    // Inventory CRUD operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInventoryItem(Inventory inventory);

    @Transaction
    @Query("SELECT * FROM inventoryTable WHERE userId = :userId")
    LiveData<List<Inventory>> getInventoryForUser(int userId);

    @Delete
    void deleteInventoryItem(Inventory inventory);
}
