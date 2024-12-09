package com.example.groupproject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SignUpDialogFragment extends DialogFragment {

    private OnSignUpListener listener;

    public interface OnSignUpListener {
        void onSignUp(String username, String password);
    }

    public void setOnSignUpListener(OnSignUpListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog_sign_up, null);
        EditText usernameInput = view.findViewById(R.id.signUpUsernameEditText);
        EditText passwordInput = view.findViewById(R.id.signUpPasswordEditText);
        Button signUpButton = view.findViewById(R.id.signUpSubmitButton);

        signUpButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (listener != null) {
                listener.onSignUp(username, password);
            }

            dismiss();
        });

        return new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setView(view)
                .setTitle("Sign Up")
                .create();
    }
}

