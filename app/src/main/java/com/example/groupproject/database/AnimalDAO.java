package com.example.groupproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.groupproject.database.entities.Animal;

import java.util.List;

@Dao
public interface AnimalDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Animal... animals);

    @Update
    void update(Animal animal);

    @Delete
    void delete(Animal animal);

    @Query("SELECT * FROM " + AppDatabase.ANIMAL_TABLE + " ORDER BY name")
    LiveData<List<Animal>> getAllAnimals();

    @Query("SELECT * FROM " + AppDatabase.ANIMAL_TABLE + " WHERE id = :animalId")
    LiveData<Animal> getAnimalById(int animalId);

    @Query("SELECT * FROM " + AppDatabase.ANIMAL_TABLE + " WHERE type = :animalType")
    LiveData<List<Animal>> getAnimalsByType(String animalType);

    @Query("SELECT * FROM " + AppDatabase.ANIMAL_TABLE + " WHERE arena = :arenaName")
    LiveData<List<Animal>> getAnimalsByArena(String arenaName);

    @Query("DELETE FROM " + AppDatabase.ANIMAL_TABLE)
    void deleteAll();
}
