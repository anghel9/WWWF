package com.example.groupproject;

import com.example.groupproject.database.AppDatabase;
import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.UserDAO;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.database.factories.AnimalFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

public class StatsActivity extends AppCompatActivity implements AnimalListFragment.OnAnimalSelectedListener {

    //private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";

    private ImageView animalImage;
    private TextView animalName;
    private TextView animalStats;
    private int LOGGED_OUT = -1;
    private AppRepository repository;
    private Animal currentAnimal;
    private int loggedInUserId = -1;
    private boolean firstAnimal = true;
    private int animalId = -1;

    static Intent statsActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, StatsActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        repository = AppRepository.getRepository(getApplication());
        animalImage = findViewById(R.id.animalImage);
        animalName = findViewById(R.id.animalName);
        animalStats = findViewById(R.id.animalStats);

        loadAnimal();


        Button swapButton = findViewById(R.id.swapButton);
        swapButton.setOnClickListener(view -> {
            //AnimalListFragment fragment = new AnimalListFragment();
            //fragment.show(getSupportFragmentManager(), "AnimalListFragment");
            loadAnimal();
        });

        Button exitButton = findViewById(R.id.statsExitButton);
        exitButton.setOnClickListener(view -> {
            //Intent intent = new Intent(StatsActivity.this, HubActivity.class);
            startActivity(HubActivity.hubActivityIntentFactory(getApplicationContext(), loggedInUserId));
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

    private void loadAnimal(){
        if(loggedInUserId == -1) {
            loggedInUserId = getIntent().getIntExtra("USER_ID", LOGGED_OUT);
        }
        if(firstAnimal) {
            firstAnimal = false;
            LiveData<User> userObserver = repository.getUserById(loggedInUserId);
            userObserver.observe(this, user -> {
                animalId = user.getCurrentCreatureId();
                currentAnimal = AnimalFactory.getAnimalById(animalId);
                updateAnimal(currentAnimal.getAnimalName(), currentAnimal.getImageResId(), currentAnimal.getStats());
            });
        } else {
            LiveData<User> userObserver = repository.getUserById(loggedInUserId);
            userObserver.observe(this, user -> {
                if(animalId >= 5){
                    animalId = 0;
                }
                user.setCurrentCreatureId(animalId + 1);
                animalId = user.getCurrentCreatureId();
                currentAnimal = AnimalFactory.getAnimalById(animalId);
                updateAnimal(currentAnimal.getAnimalName(), currentAnimal.getImageResId(), currentAnimal.getStats());
            });
        }
    }
}