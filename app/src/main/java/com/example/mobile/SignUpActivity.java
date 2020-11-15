package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void signUp(View view) {
        if (isValid()) {
            signUpSuccess();

        } else {
            signUpFailed();
        }
    }


    public void signUpFailed() {
        Toast.makeText(getBaseContext(), "Signing up is failed!", Toast.LENGTH_SHORT).show();
    }

    public void signUpSuccess() {
        Toast.makeText(getBaseContext(), "Successfully signed up!", Toast.LENGTH_SHORT).show();
    }

    public boolean isValid() {
        boolean valid = false;
        EditText nameField = (EditText) findViewById(R.id.nameSignUp);
        EditText emailField = (EditText) findViewById(R.id.emailSignUp);
        EditText passwordField = (EditText) findViewById(R.id.passwordSignUp);
        EditText confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordSignUp);
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();
        final int passwordCharacters = 8;
        if (!name.isEmpty() && !email.isEmpty() && password.length() > passwordCharacters
                && confirmPassword.equals(password)) {
            valid = true;
        } else if (name.isEmpty() && !email.isEmpty() && password.length() > passwordCharacters
                && confirmPassword.equals(password)) {
            emailField.setError("You need to enter the name!");
        } else if (!name.isEmpty() && email.isEmpty() && password.length() > passwordCharacters
                && confirmPassword.equals(password)) {
            passwordField.setError("You need to enter the email!");
        } else if (!name.isEmpty() && !email.isEmpty() && password.length() <= passwordCharacters
                && confirmPassword.equals(password)) {
            passwordField.setError("The password is less than eight characters!");
        } else if (name.isEmpty() && email.isEmpty() && password.isEmpty()
                && confirmPassword.equals(password)) {
            nameField.setError("You need to enter name!");
            emailField.setError("You need to enter email!");
            passwordField.setError("You need to enter password!");
            confirmPasswordField.setError("You need to enter confirm password!");
        } else {
            nameField.setError("Invalid credential!");
            emailField.setError("Invalid credential!");
            passwordField.setError("Invalid credential!");
            confirmPasswordField.setError("Invalid credential!");
        }
        return valid;
    }
}