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
import com.example.groupproject.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.groupproject.MAIN_ACTIVITY_USER_ID";
    private ActivityLoginBinding binding;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.loginButton.setOnClickListener(view -> verifyUser());

        binding.signUpButton.setOnClickListener(view -> handleSignUp());

    }

    private void verifyUser() {
        String username = binding.userNameLoginEditText.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(this, "Username is empty, fill it", Toast.LENGTH_SHORT).show();
            return;
        }

        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if (user != null) {
                String password = binding.passwordLoginEditText.getText().toString();
                if (password.equals(user.getPassword())) {
                    startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
                } else {
                    toastMaker("Invalid password");
                    binding.passwordLoginEditText.setText("");
                    binding.passwordLoginEditText.setSelection(0);
                }
            } else {
                toastMaker(String.format("User %s is not valid", username));
                binding.userNameLoginEditText.setText("");
                binding.userNameLoginEditText.setSelection(0);
            }
        });
    }

    private void toastMaker(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void handleSignUp() {
        String username = binding.userNameLoginEditText.getText().toString().trim();
        String password = binding.passwordLoginEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            toastMaker("Fill in all fields");
            return;
        }

        LiveData<User> existingUser = repository.getUserByUserName(username);
        existingUser.observe(this, user -> {
            if (user != null) {
                toastMaker("Username already exists. Choose a different one.");
            } else {
                User newUser = new User(username, password);
                repository.insertUser(newUser);
                toastMaker("User created successfully!");

                binding.userNameLoginEditText.setText("");
                binding.passwordLoginEditText.setText("");

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }

}

