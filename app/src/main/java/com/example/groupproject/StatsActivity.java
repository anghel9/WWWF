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

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private static final String BATTLE_ACTIVITY_BOSS_ANIMAL = "com.example.groupproject.BATTLE_ACTIVITY_BOSS_ANIMAL";

    private ImageView animalImage;
    private TextView animalName;
    private TextView animalStats;

    private UserDAO userDAO;
    private User currentUser;
    private int userId;

    public static Intent statsActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, StatsActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);  // Pass the user ID to StatsActivity
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        animalImage = findViewById(R.id.animalImage);
        animalName = findViewById(R.id.animalName);
        animalStats = findViewById(R.id.animalStats);

        //loginUser();
        userDAO = AppDatabase.getInstance(this).userDAO();

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

        // Fetch the user ID from the intentcurrentUser = userDAO.getUserById(userId);
        userId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, -1);
        if (userId <= -1) {
            Toast.makeText(this, "Invalid user!", Toast.LENGTH_SHORT).show();
            finish();
            return;

        }
        currentUser = userDAO.getUserById(userId).getValue();
        if (currentUser == null) {
            Toast.makeText(this, "Invalid user!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        // Set the initial animal
        // updateAnimal(currentUser.getAnimalName(), currentUser.getCurrentCreatureImage(), currentUser.getCurrentCreatureStats());

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