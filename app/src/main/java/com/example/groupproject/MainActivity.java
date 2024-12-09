package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.groupproject.R;
import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";

    public static final String TAG = " A";

    private ActivityMainBinding binding;

    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = AppRepository.getRepository(getApplication());
        User user1 = new User("Jerry", "PASSWORD");
        User user3 = new User("admin2", "admin2");
        user3.setAdmin(true);
        repository.insertUser(user3);
        repository.insertUser(user1);

        binding.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FOR TESTING
                //startActivity(HubActivity.hubActivityIntentFactory(getApplicationContext()));
                startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
            }
        });
    }
    static Intent mainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
}