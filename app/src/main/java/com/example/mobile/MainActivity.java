package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobile.fragments.SignInFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_sign_in, new SignInFragment())
                .commit();
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
    }
}