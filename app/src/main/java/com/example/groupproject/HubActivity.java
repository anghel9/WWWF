package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;


import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.databinding.ActivityHubBinding;

public class HubActivity extends AppCompatActivity {
    private AppRepository repository;
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private static final int LOGGED_OUT = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHubBinding binding = ActivityHubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = AppRepository.getRepository(getApplication());

        int userId = getIntent().getIntExtra("USER_ID", -1);
        if (userId == -1) {
            Toast.makeText(this, "No user logged in. Redirecting to login.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        // Fetch and observe user details
        repository.getUserById(userId).observe(this, user -> {
            if (user != null) {
                // Update UI with user data
                binding.userView.setText(String.format("Welcome, %s!", user.getUsername()));

                // If the user is not an admin, hide admin-only buttons
                if (!user.isAdmin()) {
                    binding.editUsersButton.setVisibility(View.GONE);
                }
            } else {
                Toast.makeText(this, "Error loading user data. Redirecting to login.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.worldSelectButton.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View view) {
                                                             startActivity(WorldActivity.worldActivityIntentFactory(getApplicationContext(), userId));
                                                         }
                                                     });
        binding.editPartyButton.setOnClickListener(view -> {
            startActivity(StatsActivity.statsActivityIntentFactory(getApplicationContext(), userId));
        });

        binding.signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), LOGGED_OUT));
            }
        });
    }

    static Intent hubActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, HubActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
        //return new Intent(context, HubActivity.class);
    }

}