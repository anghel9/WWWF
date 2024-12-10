package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.database.AppRepository;
import com.example.groupproject.database.entities.User;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameInput = findViewById(R.id.signUpUsernameEditText);
        passwordInput = findViewById(R.id.signUpPasswordEditText);
        Button signUpButton = findViewById(R.id.signUpSubmitButton);

        repository = AppRepository.getRepository(getApplication());

        signUpButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            repository.getUserByUserName(username).observe(this, user -> {
                if (user != null) {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    User newUser = new User(username, password);
                    repository.insertUser(newUser);
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("username", username);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            });
        });
    }

    public static Intent signUpIntentFactory(Context context) {
        return new Intent(context, SignUpActivity.class);
    }
}
