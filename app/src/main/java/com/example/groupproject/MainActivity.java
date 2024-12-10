package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        // Debug and insert users only if they don't exist
        new Thread(() -> {
            if (repository.isUsernameTaken("Jerry") == 0) {
                repository.insertUser(new User("Jerry", "PASSWORD"));
                Log.d(TAG, "Inserted user Jerry");
            }
            if (repository.isUsernameTaken("admin2") == 0) {
                User admin = new User("admin2", "admin2");
                admin.setAdmin(true);
                repository.insertUser(admin);
                Log.d(TAG, "Inserted admin user");
            }
        }).start();

        binding.actionButton.setOnClickListener(view -> {
            startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
        });
    }

    public static Intent mainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
}
