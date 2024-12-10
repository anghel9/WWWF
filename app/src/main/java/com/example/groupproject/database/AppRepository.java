package com.example.groupproject.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.groupproject.database.entities.Animal;
import com.example.groupproject.database.entities.GameProgress;
import com.example.groupproject.database.entities.Inventory;
import com.example.groupproject.database.entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRepository {

    private static volatile AppRepository repository;
    private final UserDAO userDAO;
    private final AnimalDAO animalDAO;
    private final GameProgressDAO gameProgressDAO;
    private final InventoryDAO inventoryDAO;
    private final ExecutorService executorService;

    private AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDAO = db.userDAO();
        animalDAO = db.animalDAO();
        gameProgressDAO = db.gameProgressDAO();
        inventoryDAO = db.inventoryDAO();
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

    public int isUsernameTaken(String username) {
        return userDAO.isUsernameTaken(username);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userDAO.authenticateUser(username, password);
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

    // AnimalDAO Methods
    public LiveData<List<Animal>> getAllAnimals() {
        return animalDAO.getAllAnimals();
    }

    public LiveData<Animal> getAnimalById(int id) {
        return animalDAO.getAnimalById(id);
    }

    public LiveData<List<Animal>> getAnimalsByType(String type) {
        return animalDAO.getAnimalsByType(type);
    }

    public LiveData<List<Animal>> getAnimalsByArena(String arena) {
        return animalDAO.getAnimalsByArena(arena);
    }

    public void insertAnimal(Animal animal) {
        executorService.execute(() -> animalDAO.insert(animal));
    }

    public void updateAnimal(Animal animal) {
        executorService.execute(() -> animalDAO.update(animal));
    }

    public void deleteAnimal(Animal animal) {
        executorService.execute(() -> animalDAO.delete(animal));
    }

    public void deleteAllAnimals() {
        executorService.execute(animalDAO::deleteAll);
    }
}
