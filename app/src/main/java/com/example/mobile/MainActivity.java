package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView signUp = (TextView) findViewById(R.id.goToSignUp);

        signUp.setOnClickListener(view -> {
            Intent signUpIntent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(signUpIntent);
        });
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
    }

    public void logIn(View view) {
        if (isValid()) {
            loginSuccess();
        } else {
            loginFailed();
        }
    }

    public void loginFailed() {
        Toast.makeText(getBaseContext(), "Logging is failed!", Toast.LENGTH_SHORT).show();
    }

    public void loginSuccess() {
        Toast.makeText(getBaseContext(), "Successfully logged in!", Toast.LENGTH_SHORT).show();
    }

    public boolean isValid() {
        boolean valid = false;
        EditText emailField = findViewById(R.id.emailSignIn);
        EditText passwordField = findViewById(R.id.passwordSignIn);
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        boolean emailPattern = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        final int passwordCharacters = 8;
        if (!email.isEmpty() && emailPattern && password.length() > passwordCharacters) {
            valid = true;
        } else if (email.isEmpty() && password.length() > passwordCharacters) {
            emailField.setError("You need to enter email!");
        } else if (emailPattern && password.isEmpty()) {
            passwordField.setError("You need to enter password!");
        } else if (email.isEmpty() && password.isEmpty()) {
            emailField.setError("You need to enter email!");
            passwordField.setError("You need to enter password!");
        } else {
            emailField.setError("Invalid credential!");
            passwordField.setError("Invalid credential!");
        }
        return valid;
    }
}