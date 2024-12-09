package com.example.groupproject;

import static com.example.groupproject.database.factories.AnimalFactory.getAnimalById;

import com.example.groupproject.database.entities.User;
import com.example.groupproject.database.factories.AnimalFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StatsActivity extends AppCompatActivity implements AnimalListFragment.OnAnimalSelectedListener {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";

    private ImageView animalImage;
    private TextView animalName;
    private TextView animalStats;

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

        Animal playerAnimal = AnimalFactory.getAnimalById(User.getCurrentCreatureId());
        updateAnimal(playerAnimal.getAnimalName(), playerAnimal.getImageResId(), playerAnimal.getStats());
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