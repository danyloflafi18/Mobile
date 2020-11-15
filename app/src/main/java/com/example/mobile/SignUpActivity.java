package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void signUp(View view) {
        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        if (validate()) {
            loginSuccess();
            return;
        } else {
            signUpButton.setEnabled(false);
            loginFailed();
        }
    }


    public void loginFailed() {
        Button loginButton = (Button) findViewById(R.id.signUpButton);
        Toast.makeText(getBaseContext(), "Signing up is failed!", Toast.LENGTH_SHORT).show();
        loginButton.setEnabled(true);
    }

    public void loginSuccess() {
        Button loginButton = (Button) findViewById(R.id.signUpButton);
        Toast.makeText(getBaseContext(), "Successfully signed up!", Toast.LENGTH_SHORT).show();
        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = false;
        EditText name = (EditText) findViewById(R.id.nameSignUp);
        EditText email = (EditText) findViewById(R.id.emailSignUp);
        EditText password = (EditText) findViewById(R.id.passwordSignUp);
        EditText confirmPassword = (EditText) findViewById(R.id.confirmPasswordSignUp);
        String psw = password.getText().toString();
        String confirmPsw = confirmPassword.getText().toString();
        if (name.getText().toString().length() > 0
                && email.getText().toString().length() > 0
                && password.getText().toString().length() > 8
                && confirmPsw.equals(psw)) {
            valid = true;
        } else if (name.getText().toString().length() == 0
                && email.getText().toString().length() > 0
                && password.getText().toString().length() > 8
                && confirmPsw.equals(psw)) {
            valid = false;
            email.setError("You need to enter the name!");
        } else if (name.getText().toString().length() > 0
                && email.getText().toString().length() == 0
                && password.getText().toString().length() > 8
                && confirmPsw.equals(psw)) {
            valid = false;
            password.setError("You need to enter the email!");
        } else if (name.getText().toString().length() > 0
                && email.getText().toString().length() > 0
                && password.getText().toString().length() <= 8
                && confirmPsw.equals(psw)) {
            valid = false;
            password.setError("The password is less than eight characters!");
        } else if (name.getText().toString().length() == 0
                && email.getText().toString().length() == 0
                && password.getText().toString().length() == 0
                && confirmPsw.equals(psw)) {
            valid = false;
            name.setError("You need to enter name!");
            email.setError("You need to enter email!");
            password.setError("You need to enter password!");
            confirmPassword.setError("You need to enter confirm password!");
        } else {
            valid = false;
            name.setError("Invalid credential!");
            email.setError("Invalid credential!");
            password.setError("Invalid credential!");
            confirmPassword.setError("Invalid credential!");
        }
        return valid;
    }
}