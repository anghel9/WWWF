package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.groupproject.database.factories.AnimalFactory;

import androidx.appcompat.app.AppCompatActivity;

public class WorldActivity extends AppCompatActivity {

    private Button bossButton;
    private Button exitButton;
    private Button forestBattleButton;
    private Button beachBattleButton;
    private Button atlantaBattleButton;

    static Intent worldActivityIntentFactory(Context context) {
        Intent Intent = new Intent(context, WorldActivity.class);
        return Intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        exitButton = findViewById(R.id.ExitButton);
        exitButton.setOnClickListener(v -> {
            finish();
        });

        // Initialize the button to go to the boss fight
        bossButton = findViewById(R.id.BossButton);
        bossButton.setOnClickListener(v -> {
            // Create a custom boss animal for this arena
            Animal bossAnimal = AnimalFactory.createBossAnimal();

            // Start the BattleActivity with the custom Boss Animal
            Intent battleIntent = BattleActivity.battleActivityIntentFactory(this);
            startActivity(battleIntent);
        });
    }
}