package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.databinding.ActivityDeleteUserBinding;

public class DeleteUserActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private ActivityDeleteUserBinding binding;
    private int loggedInUserId = -1;
    private User user;
    private static final int LOGGED_OUT = -1;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = AppRepository.getRepository(getApplication());
        loginUser();
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(HubActivity.hubActivityIntentFactory(getApplicationContext(), loggedInUserId));
            }
        });
        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptDelete();
            }
        });
    }

    void attemptDelete(){
        String username = binding.userNameDeleteEditText.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(this, "Username is empty, fill it", Toast.LENGTH_SHORT).show();
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if(user == null){
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
                return;
            }
            if(user.isAdmin()){
                Toast.makeText(this, "Cannot delete admin user", Toast.LENGTH_SHORT).show();
                return;
            }
            repository.deleteUser(user);
        });
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

    static Intent deleteUserActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, DeleteUserActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
        //return new Intent(context, HubActivity.class);
    }
}