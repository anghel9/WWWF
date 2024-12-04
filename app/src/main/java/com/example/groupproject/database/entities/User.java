package com.example.groupproject.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.example.groupproject.database.AppDatabase;
import java.util.Objects;

@Entity(tableName = "userTable")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;

    private String password;

    private boolean isAdmin;

    private int highestArena = 0;

    public User() {}

    @Ignore
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                highestArena == user.highestArena &&
                isAdmin == user.isAdmin &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, highestArena, isAdmin);
    }

    public int getHighestArena() {
        return highestArena;
    }

    public void setHighestArena(int highestArena) {
        this.highestArena = highestArena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}