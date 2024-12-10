package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;


import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.databinding.ActivityHubBinding;

public class HubActivity extends AppCompatActivity {
    private AppRepository repository;
    private User user;
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private static final int LOGGED_OUT = -1;
    private int loggedInUserId = LOGGED_OUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActivityHubBinding binding;


        super.onCreate(savedInstanceState);
        binding = ActivityHubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

//        loginUser();
//        if(user.isAdmin()){
//            binding.editUsersButton.setVisibility(View.GONE);
//        }
        binding.worldSelectButton.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View view) {
                                                             startActivity(WorldActivity.worldActivityIntentFactory(getApplicationContext()));
                                                         }
                                                     });
        binding.editPartyButton.setOnClickListener(view -> {
            startActivity(StatsActivity.statsActivityIntentFactory(getApplicationContext()));
        });

        binding.signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), 0));
            }
        });
        //TODO implement intent factories once they exist
//        binding.editPartyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(StatsActivity);
//            }
//        });
//        binding.worldSelectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(WorldActivity);
//            }
//        });
    }

    private void loginUser() {
        if(loggedInUserId == LOGGED_OUT){
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if(loggedInUserId == LOGGED_OUT){
            return;
        }
        LiveData<User> userObserver = repository.getUserById(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if(this.user != null){
                invalidateOptionsMenu();
            }
        });
    }

    static Intent hubActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, HubActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
        //return new Intent(context, HubActivity.class);
    }

}