package com.example.groupproject;

import com.example.groupproject.database.AppDatabase;
import com.example.groupproject.database.UserDAO;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.database.factories.AnimalFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StatsActivity extends AppCompatActivity implements AnimalListFragment.OnAnimalSelectedListener {

    //private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";

    private ImageView animalImage;
    private TextView animalName;
    private TextView animalStats;

    public static Intent statsActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, StatsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        animalImage = findViewById(R.id.animalImage);
        animalName = findViewById(R.id.animalName);
        animalStats = findViewById(R.id.animalStats);

        Button swapButton = findViewById(R.id.swapButton);
        swapButton.setOnClickListener(view -> {
            AnimalListFragment fragment = new AnimalListFragment();
            fragment.show(getSupportFragmentManager(), "AnimalListFragment");
        });

        Button exitButton = findViewById(R.id.statsExitButton);
        exitButton.setOnClickListener(view -> {
            Intent intent = new Intent(StatsActivity.this, HubActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onAnimalSelected(String name, int imageResId, String stats){
        updateAnimal(name, imageResId, stats);
    }

    private void updateAnimal(String name, int imageResId, String stats){
        animalImage.setImageResource(imageResId);
        animalName.setText(name);
        animalStats.setText("Stats: " + stats);
    }
}