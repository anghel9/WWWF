package com.example.groupproject.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.groupproject.database.AppDatabase;
import java.util.Objects;

@Entity(tableName = AppDatabase.ANIMAL_TABLE)
public class Animal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name = "";
    private String type = "";
    private int arena; // Assuming arena is an integer ID
    private double hp = 100.0;
    private double attack = 25.25;
    private double accuracy = 10.0; // Percentage (0-100)

    // Constructor
    public Animal(String name, String type, int arena, double hp, double attack, double accuracy) {
        this.name = name;
        this.type = type;
        this.arena = arena;
        this.hp = hp;
        this.attack = attack;
        setAccuracy(accuracy); // Use setter to validate accuracy
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getArena() {
        return arena;
    }

    public void setArena(int arena) {
        this.arena = arena;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        if (accuracy < 0 || accuracy > 100) {
            throw new IllegalArgumentException("Accuracy must be between 0 and 100.");
        }
        this.accuracy = accuracy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;
        return id == animal.id &&
                Double.compare(animal.hp, hp) == 0 &&
                Double.compare(animal.attack, attack) == 0 &&
                Double.compare(animal.accuracy, accuracy) == 0 &&
                Objects.equals(name, animal.name) &&
                Objects.equals(type, animal.type) &&
                Objects.equals(arena, animal.arena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, arena, hp, attack, accuracy);
    }
}
