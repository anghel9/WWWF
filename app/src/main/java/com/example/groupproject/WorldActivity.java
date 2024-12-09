package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        // Wire up buttons
        findViewById(R.id.ExitButton).setOnClickListener(view -> handleExit());
        findViewById(R.id.ForestBattleButton).setOnClickListener(view -> navigateTo(BattleActivity.class));
        findViewById(R.id.BeachBattleButton).setOnClickListener(view -> navigateTo(BattleActivity.class));
        findViewById(R.id.AtlantaBattleButton).setOnClickListener(view -> navigateTo(BattleActivity.class));
        findViewById(R.id.BossButton).setOnClickListener(view -> navigateTo(BattleActivity.class));
    }

    private void handleExit() {
        finish();
    }

    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }
}
