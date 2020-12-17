package com.example.mobile.peopleapi.presentation.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.fragments.StartScreenFragment;
import com.example.mobile.peopleapi.presentation.people_list.OnNewsListener;

public class StartScreenActivity extends AppCompatActivity implements OnNewsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activityStartScreen, new StartScreenFragment())
                .commit();
    }

    @Override
    public void onNewsClick(int position) {
        startActivity(new Intent(this, NewsActivity.class));
    }
}