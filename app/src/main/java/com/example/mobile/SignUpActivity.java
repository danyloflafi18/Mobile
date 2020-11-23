package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobile.fragments.SignUpFragment;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_sign_up, new SignUpFragment())
                .commit();
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}