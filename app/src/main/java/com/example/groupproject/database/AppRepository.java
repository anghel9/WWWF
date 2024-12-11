package com.example.groupproject.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.groupproject.Animal;
import com.example.groupproject.database.entities.GameProgress;
import com.example.groupproject.database.entities.Inventory;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.database.factories.AnimalFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRepository {

    private static volatile AppRepository repository;
    private final UserDAO userDAO;
    private final GameProgressDAO gameProgressDAO; // Add reference to GameProgressDAO
    private final InventoryDAO inventoryDAO;       // Add reference to InventoryDAO
    private final ExecutorService executorService;

    private AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDAO = db.userDAO();
        gameProgressDAO = db.gameProgressDAO(); // Initialize GameProgressDAO
        inventoryDAO = db.inventoryDAO();       // Initialize InventoryDAO
        executorService = Executors.newFixedThreadPool(4);
    }

    // Singleton pattern
    public static AppRepository getRepository(Application application) {
        if (repository == null) {
            synchronized (AppRepository.class) {
                if (repository == null) {
                    repository = new AppRepository(application);
                }
            }
        }
        return repository;
    }

    // UserDAO Methods
    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUsername(username);
    }

    public LiveData<User> getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public User getUserByIdNonLive(int id) {
        return userDAO.getUserByIdNonLive(id);

    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void insertUser(User user) {
        executorService.execute(() -> userDAO.insert(user));
    }

    public void deleteUser(User user) {
        executorService.execute(() -> userDAO.delete(user));
    }

    public void deleteAllUsers() {
        executorService.execute(userDAO::deleteAll);
    }

    // GameProgressDAO Methods
    public LiveData<GameProgress> getGameProgressForUser(int userId) {
        return gameProgressDAO.getGameProgressForUser(userId); // Use GameProgressDAO
    }

    public void insertGameProgress(GameProgress gameProgress) {
        executorService.execute(() -> gameProgressDAO.insertGameProgress(gameProgress)); // Use GameProgressDAO
    }

    public void deleteGameProgress(GameProgress gameProgress) {
        executorService.execute(() -> gameProgressDAO.deleteGameProgress(gameProgress)); // Use GameProgressDAO
    }

    // InventoryDAO Methods
    public LiveData<List<Inventory>> getInventoryForUser(int userId) {
        return inventoryDAO.getInventoryForUser(userId); // Use InventoryDAO
    }

    public void insertInventoryItem(Inventory inventory) {
        executorService.execute(() -> inventoryDAO.insertInventoryItem(inventory)); // Use InventoryDAO
    }

    public void deleteInventoryItem(Inventory inventory) {
        executorService.execute(() -> inventoryDAO.deleteInventoryItem(inventory)); // Use InventoryDAO
    }

    public void updateAssignedCreature(int creatureId, int userId) {
        userDAO.updateAssignedCreature(creatureId, userId);
    }

    public Animal getAssignedCreature(int userId) {
        User user = userDAO.getUserById(userId).getValue();
        if (user != null) {
            int creatureId = user.getCurrentCreatureId();
            return AnimalFactory.getAnimalById(creatureId);
        }
        return null;
    }

    public int isUsernameTaken(String username) {
        return userDAO.isUsernameTaken(username);
    }

    public User authenticateUser(String username, String password) {
        return userDAO.authenticateUser(username, password);
    }

    public int countUsers() {
        return userDAO.countUsers();
    }

    public LiveData<User> getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

}
