package com.example.groupproject;

import com.example.groupproject.database.AppDatabase;
import com.example.groupproject.database.UserDAO;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.database.factories.AnimalFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    static Intent statsActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, StatsActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        animalImage = findViewById(R.id.animalImage);
        animalName = findViewById(R.id.animalName);
        animalStats = findViewById(R.id.animalStats);


        int userId = getIntent().getIntExtra("USER_ID", -1);
        if (userId == -1) {
            Toast.makeText(this, "No user logged in. Redirecting to login.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        Log.d("StatsActivity", "Received USER_ID: " + userId);

        Button swapButton = findViewById(R.id.swapButton);
        swapButton.setOnClickListener(view -> {
            AnimalListFragment fragment = new AnimalListFragment();
            fragment.show(getSupportFragmentManager(), "AnimalListFragment");
        });

        Button exitButton = findViewById(R.id.statsExitButton);
        exitButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HubActivity.class);
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