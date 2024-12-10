package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.entities.User;
import com.example.groupproject.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final int SIGN_UP_REQUEST_CODE = 1;

    private ActivityLoginBinding binding;
    private AppRepository repository;

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

        if (username.isEmpty()) {
            Toast.makeText(this, "Username is empty, fill it", Toast.LENGTH_SHORT).show();
            return;
        }

        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if (user != null) {
                String password = binding.passwordLoginEditText.getText().toString();
                if (password.equals(user.getPassword())) {
                    startActivity(MainActivity.mainActivityIntentFactory(this, user.getId()));
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

    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
