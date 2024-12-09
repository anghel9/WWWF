package com.example.groupproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.groupproject.database.AppDatabase;
import com.example.groupproject.database.UserDAO;
import com.example.groupproject.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class UserDAOTest {

    private AppDatabase database;
    private UserDAO userDAO;

    @Before
    public void setUp(){
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        userDAO = database.userDAO();
    }

    @After
    public void tearDown(){
        database.close();
    }

    @Test
    public void testInsertUser(){
        User user = new User("testuser1", "testuser1");
        user.setAdmin(true);
        userDAO.insert(user);

        List<User> users = userDAO.getAllUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        User retrievedUser = users.get(0);
        assertEquals("testuser1", retrievedUser.getUsername());
        assertEquals("testuser1", retrievedUser.getPassword());
        assertTrue(retrievedUser.isAdmin());
    }

    @Test
    public void testUpdateUser(){
        User user = new User("testuser1", "password123");
        user.setHighestArena(3);
        userDAO.insert(user);

        List<User> users = userDAO.getAllUsers();
        User retrievedUser = users.get(0);
        retrievedUser.setHighestArena(5);
        retrievedUser.setPassword("newpassword123");
        userDAO.update(retrievedUser);

        User updatedUser = userDAO.getUserById(retrievedUser.getId()).getValue();
        assertNotNull(updatedUser);
        assertEquals(5, updatedUser.getHighestArena());
        assertEquals("newpassword123", updatedUser.getPassword());
    }

    @Test
    public void testDeleteUser(){
        User user = new User("testuser1", "password1");
        user.setAdmin(true);
        userDAO.insert(user);

        List<User> users = userDAO.getAllUsers();
        User retrievedUser = users.get(0);
        userDAO.delete(retrievedUser);

        List<User> remainingUsers = userDAO.getAllUsers();
        assertTrue(remainingUsers.isEmpty());
    }
}
