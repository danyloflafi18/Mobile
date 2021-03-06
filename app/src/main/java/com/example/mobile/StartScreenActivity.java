package com.example.mobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.fragments.StartScreenFragment;

public class StartScreenActivity extends AppCompatActivity {



import java.util.Objects;

public class StartScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.fragment_start_screen);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.startScreen, new StartScreenFragment())
                .commit();


    }
        setContentView(R.layout.activity_start_screen);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.startScreenContainer, new StartScreenFragment())
                .commit();
    }
}