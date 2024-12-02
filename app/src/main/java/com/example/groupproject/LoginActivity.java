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

    private ActivityLoginBinding binding;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.loginButton.setOnClickListener(view -> verifyUser());
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
                    // Assuming this factory method is correct
                    startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
                } else {
                    toastMaker("Invalid password");
                    binding.passwordLoginEditText.setText("");  // Clear the password field
                    binding.passwordLoginEditText.setSelection(0); // Move cursor to start
                }
            } else {
                toastMaker(String.format("User %s is not valid", username));
                binding.userNameLoginEditText.setText("");  // Clear username field
                binding.userNameLoginEditText.setSelection(0); // Move cursor to start
            }
        });
    }

    private void toastMaker(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}

