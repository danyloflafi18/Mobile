package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void logIn(View view) {
        Button loginButton = (Button) findViewById(R.id.signInButton);
        if (validate()) {
            loginSuccess();
            return;
        }
        else {
            loginButton.setEnabled(false);
            loginFailed();
        }
    }

    public void loginFailed() {
        Button loginButton = (Button) findViewById(R.id.signInButton);
        Toast.makeText(getBaseContext(), "Logging is failed!", Toast.LENGTH_SHORT).show();
        loginButton.setEnabled(true);
    }
    public void loginSuccess() {
        Button loginButton = (Button) findViewById(R.id.signInButton);
        Toast.makeText(getBaseContext(), "Successfully logged in!",Toast.LENGTH_SHORT).show();
        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = false;
        EditText email = (EditText) findViewById(R.id.emailSignIn);
        EditText password = (EditText) findViewById(R.id.passwordSignIn);
        String emailCred = "admin";
        if (email.getText().toString().equals(emailCred)
                && password.getText().toString().length() > 8) {
            valid = true;
        } else if (email.getText().toString().length() == 0
                && password.getText().toString().length() > 8) {
            valid = false;
            email.setError("You need to enter email!");
        } else if (email.getText().toString().equals(emailCred)
                && password.getText().toString().length() == 0) {
            valid = false;
            password.setError("You need to enter password!");
        } else if (email.getText().toString().length() == 0
                && password.getText().toString().length() == 0) {
            valid = false;
            email.setError("You need to enter email!");
            password.setError("You need to enter password!");
        } else if(email.getText().toString().equals(emailCred)
                && password.getText().toString().length() <= 8){
            password.setError("The password is less than nine characters!");
        }else {
            valid = false;
            email.setError("Invalid credential!");
            password.setError("Invalid credential!");
        }
        return valid;
    }

}