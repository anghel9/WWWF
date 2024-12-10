package com.example.groupproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.groupproject.database.AppDatabase;
import com.example.groupproject.database.GameProgressDAO;
import com.example.groupproject.database.entities.GameProgress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GameProgressDAOTest {
    private AppDatabase testDatabase;
    private GameProgressDAO gameProgressDAO;

    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        testDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        gameProgressDAO = testDatabase.gameProgressDAO();
    }

    @After
    public void closeDb(){
        testDatabase.close();
    }

    @Test
    public void testInsertGameProgress(){
        GameProgress progress = new GameProgress();
        progress.setUserId(1);
        progress.setLevel(5);
        progress.setExperiencePoints(100);

        gameProgressDAO.insertGameProgress(progress);
        GameProgress result = LiveDataTestUtil.getValue(gameProgressDAO.getGameProgressForUser(1));

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals(5, result.getLevel());
        assertEquals(100, result.getExperiencePoints());
    }
}
