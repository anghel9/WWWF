package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.groupproject.AtlantaBattleActivity;
import com.example.groupproject.BeachBattleActivity;
import com.example.groupproject.BossBattleActivity;
import com.example.groupproject.ForestBattleActivity;
import com.example.groupproject.database.factories.AnimalFactory;

import androidx.appcompat.app.AppCompatActivity;

public class WorldActivity extends AppCompatActivity {

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
            Intent battleIntent = ForestBattleActivity.forestBattleIntentFactory(this);
            startActivity(battleIntent);
        });

        beachBattleButton = findViewById(R.id.BeachBattleButton);
        beachBattleButton.setOnClickListener(v -> {
            Intent battleIntent = BeachBattleActivity.beachBattleIntentFactory(this);
            startActivity(battleIntent);
        });

        atlantaBattleButton = findViewById(R.id.AtlantaBattleButton);
        atlantaBattleButton.setOnClickListener(v -> {
            Intent battleIntent = AtlantaBattleActivity.atlantaBattleIntentFactory(this);
            startActivity(battleIntent);
        });

        bossButton = findViewById(R.id.BossButton);
        bossButton.setOnClickListener(v -> {
            Intent battleIntent = BossBattleActivity.bossBattleIntentFactory(this);
            startActivity(battleIntent);
        });
    }

    static Intent worldActivityIntentFactory(Context context) {
        return new Intent(context, WorldActivity.class);
    }
}