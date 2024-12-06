package com.example.groupproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.groupproject.database.entities.Inventory;

import java.util.List;

@Dao
public interface InventoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInventoryItem(Inventory inventory);

    @Query("SELECT * FROM " + AppDatabase.INVENTORY_TABLE + " WHERE userId = :userId")
    LiveData<List<Inventory>> getInventoryForUser(int userId);

    @Query("SELECT * FROM " + AppDatabase.INVENTORY_TABLE)
    LiveData<List<Inventory>> getAllInventory();

    @Delete
    void deleteInventoryItem(Inventory inventory);

    @Query("DELETE FROM " + AppDatabase.INVENTORY_TABLE)
    void deleteAllInventory();
}
