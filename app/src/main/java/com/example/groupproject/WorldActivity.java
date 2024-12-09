package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.groupproject.databinding.ActivityWorldBinding;

public class WorldActivity extends AppCompatActivity {

    public static Intent worldActivityIntentFactory(Context applicationContext) {
        return new Intent(applicationContext, WorldActivity.class);
        //return new Intent(context, WorldActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityWorldBinding binding = ActivityWorldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up click listeners
        binding.AtlantaBattleButton.setOnClickListener(view ->
                startActivity(BattleActivity.battleActivityIntentFactory(this))
        );

        binding.ForestBattleButton.setOnClickListener(view ->
                startActivity(BattleActivity.battleActivityIntentFactory(this))
        );

        binding.BeachBattleButton.setOnClickListener(view ->
                startActivity(BattleActivity.battleActivityIntentFactory(this))
        );

        binding.BossButton.setOnClickListener(view ->
                startActivity(BattleActivity.battleActivityIntentFactory(this))
        );

        binding.ExitButton.setOnClickListener(view -> handleExit());
    }

    private void handleExit() {
        finish();
    }
}
