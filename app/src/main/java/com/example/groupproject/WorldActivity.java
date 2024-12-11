package com.example.groupproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.groupproject.AtlantaBattleActivity;
import com.example.groupproject.BeachBattleActivity;
import com.example.groupproject.BossBattleActivity;
import com.example.groupproject.ForestBattleActivity;
import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.factories.AnimalFactory;

import androidx.appcompat.app.AppCompatActivity;

public class WorldActivity extends AppCompatActivity {

    private AppRepository repository;
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private Button bossButton;
    private Button exitButton;
    private Button forestBattleButton;
    private Button beachBattleButton;
    private Button atlantaBattleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        exitButton = findViewById(R.id.ExitButton);
        exitButton.setOnClickListener(v -> {
            finish();
        });

        forestBattleButton = findViewById(R.id.ForestBattleButton);
        forestBattleButton.setOnClickListener(v -> {
            int userId = getIntent().getIntExtra("User_Name", -1);
            Intent battleIntent = ForestBattleActivity.forestBattleIntentFactory(getApplicationContext(), userId);
            battleIntent.putExtra("USER_ID", userId);
            startActivity(battleIntent);
        });

        beachBattleButton = findViewById(R.id.BeachBattleButton);
        beachBattleButton.setOnClickListener(v -> {
            int userId = getIntent().getIntExtra("User_Name", -1); // Assuming "User_Name" is the key

            Intent battleIntent = BeachBattleActivity.beachBattleIntentFactory(getApplicationContext(), userId);
            battleIntent.putExtra("USER_ID", userId); // Add the userId to the intent
            startActivity(battleIntent);
        });

        atlantaBattleButton = findViewById(R.id.AtlantaBattleButton);
        atlantaBattleButton.setOnClickListener(v -> {
            int userId = getIntent().getIntExtra("User_Name", -1);
            Intent battleIntent = AtlantaBattleActivity.atlantaBattleIntentFactory(getApplicationContext(), userId);
            battleIntent.putExtra("USER_ID", userId);
            startActivity(battleIntent);
        });

        bossButton = findViewById(R.id.BossButton);
        bossButton.setOnClickListener(v -> {
            int userId = getIntent().getIntExtra("User_Name", -1);
            Intent battleIntent = BossBattleActivity.bossBattleIntentFactory(getApplicationContext(), userId);
            battleIntent.putExtra("USER_ID", userId);
            startActivity(battleIntent);
        });
    }

    static Intent worldActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WorldActivity.class);
        intent.putExtra("User_Name", userId);
        return intent;
    }
}