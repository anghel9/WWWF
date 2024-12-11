package com.example.groupproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.groupproject.AtlantaBattleActivity;
import com.example.groupproject.BeachBattleActivity;
import com.example.groupproject.BossBattleActivity;
import com.example.groupproject.ForestBattleActivity;
import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.factories.AnimalFactory;

import androidx.appcompat.app.AppCompatActivity;

public class WorldActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private Button bossButton;
    private Button exitButton;
    private Button forestBattleButton;
    private Button beachBattleButton;
    private Button atlantaBattleButton;

    static Intent worldActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, WorldActivity.class);
        intent.putExtra("User_ID", userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        exitButton = findViewById(R.id.ExitButton);
        exitButton.setOnClickListener(v -> {
            finish();
        });

        int userId = getIntent().getIntExtra("USER_ID", -1);
        Log.d("HubActivity", "Received USER_ID: " + userId);
        if (userId == -1) {
            Toast.makeText(this, "No user logged in. Redirecting to login.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        forestBattleButton = findViewById(R.id.ForestBattleButton);
        forestBattleButton.setOnClickListener(v -> {
            Intent forestBattleIntent = ForestBattleActivity.forestBattleIntentFactory(getApplicationContext(), userId);
            startActivity(forestBattleIntent);
        });

        beachBattleButton = findViewById(R.id.BeachBattleButton);
        beachBattleButton.setOnClickListener(v -> {
            Intent beachBattleIntent = BeachBattleActivity.beachBattleIntentFactory(getApplicationContext(), userId);
            startActivity(beachBattleIntent);
        });

        atlantaBattleButton = findViewById(R.id.AtlantaBattleButton);
        atlantaBattleButton.setOnClickListener(v -> {
            Intent atlantaBattleIntent = AtlantaBattleActivity.atlantaBattleIntentFactory(getApplicationContext(), userId);
            startActivity(atlantaBattleIntent);
        });

        bossButton = findViewById(R.id.BossButton);
        bossButton.setOnClickListener(v -> {
            Intent bossBattleIntent = BossBattleActivity.bossBattleIntentFactory(getApplicationContext(), userId);
            startActivity(bossBattleIntent);
        });
    }
}