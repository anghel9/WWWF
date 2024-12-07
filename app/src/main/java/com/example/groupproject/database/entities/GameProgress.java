package com.example.groupproject.database.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import com.example.groupproject.database.AppDatabase;

@Entity(
        tableName = AppDatabase.GAME_PROGRESS_TABLE,
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE)
)
public class GameProgress {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId; // Foreign key to User
    private int level;
    private int experiencePoints;

    public GameProgress() {}

    public GameProgress(int userId, int level, int experiencePoints) {
        this.userId = userId;
        this.level = level;
        this.experiencePoints = experiencePoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
