package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.groupproject.databinding.ActivityWorldBinding;

public class WorldActivity extends AppCompatActivity {

    public static Intent worldActivityIntentFactory(Context applicationContext) {
        return new Intent(applicationContext, WorldActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        ActivityWorldBinding binding = ActivityWorldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.AtlantaBattleButton.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View view) {
                                                             startActivity(BattleActivity.battleActivityIntentFactory(getApplicationContext()));
                                                         }
                                                     });

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
