package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.groupproject.database.AppDatabase;
import com.example.groupproject.database.UserDAO;
import com.example.groupproject.database.factories.AnimalFactory;

import androidx.appcompat.app.AppCompatActivity;

public class WorldActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private UserDAO userDAO;
    private int userId;

    private Button bossButton;

    static Intent worldActivityIntentFactory(Context context, int userId) {
        Intent Intent = new Intent(context, WorldActivity.class);
        Intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return Intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        userDAO = AppDatabase.getInstance(this).userDAO();
        userId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, -1);

        if (userId <= -1) {
            Toast.makeText(this, "Invalid user!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize the button to go to the boss fight
        bossButton = findViewById(R.id.BossButton);
        bossButton.setOnClickListener(v -> {
            // Create a custom boss animal for this arena
            Animal bossAnimal = AnimalFactory.createBossAnimal();

            // Start the BattleActivity with the custom Boss Animal
            Intent battleIntent = BattleActivity.battleActivityIntentFactory(this, userId, bossAnimal);
            startActivity(battleIntent);
        });
    }
}