package com.example.groupproject.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.groupproject.database.entities.Animal;
import com.example.groupproject.database.entities.GameProgress;
import com.example.groupproject.database.entities.Inventory;
import com.example.groupproject.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Animal.class, GameProgress.class, Inventory.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String USER_TABLE = "userTable";
    public static final String ANIMAL_TABLE = "animalTable";
    public static final String GAME_PROGRESS_TABLE = "gameProgressTable";
    public static final String INVENTORY_TABLE = "inventoryTable";

    private static final String DATABASE_NAME = "AppDatabase";

    private static volatile AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(prePopulateDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback prePopulateDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(AppDatabase.class.getSimpleName(), "Database created");
            databaseWriteExecutor.execute(() -> {
                // Populate default data
                UserDAO userDao = INSTANCE.userDAO();
                userDao.deleteAll(); // Clear any existing data
                addDefaultUsers(userDao);
            });
        }

        private void addDefaultUsers(UserDAO userDao) {
            User admin = new User("admin1", "admin1");
            admin.setAdmin(true);
            userDao.insert(admin);

            User testUser = new User("testUser1", "testUser1");
            userDao.insert(testUser);

            Log.i(AppDatabase.class.getSimpleName(), "Default users added");
        }
    };

    // DAOs
    public abstract UserDAO userDAO();
    public abstract AnimalDAO animalDAO();

}
