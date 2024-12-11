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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private static final int SIGN_UP_REQUEST_CODE = 1;
    private ActivityLoginBinding binding;
    private AppRepository repository;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.loginButton.setOnClickListener(view -> verifyUser());
        binding.signUpButton.setOnClickListener(view -> {
            Intent intent = SignUpActivity.signUpIntentFactory(this);
            startActivityForResult(intent, SIGN_UP_REQUEST_CODE);
        });
    }

    private void verifyUser() {
        String username = binding.userNameLoginEditText.getText().toString().trim();
        String password = binding.passwordLoginEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute(() -> {
            User user = repository.getUserByUsernameAndPassword(username, password);
            runOnUiThread(() -> {
                if (user != null) {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                    startActivity(HubActivity.hubActivityIntentFactory(getApplicationContext()));
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_UP_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String username = data.getStringExtra("username");
            if (username != null) {
                binding.userNameLoginEditText.setText(username);
                Toast.makeText(this, "Sign up successful! Please log in.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }

}

