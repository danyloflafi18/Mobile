package com.example.mobile.peopleapi.presentation.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.clickListener.OnNewsListener;
import com.example.mobile.peopleapi.presentation.clickListener.OnProfileListener;
import com.example.mobile.peopleapi.presentation.fragments.MyProfileFragment;
import com.example.mobile.peopleapi.presentation.fragments.StartScreenFragment;

public class StartScreenActivity extends AppCompatActivity implements OnNewsListener, OnProfileListener {
    private MyProfileFragment myProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        myProfileFragment = new MyProfileFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activityStartScreen, new StartScreenFragment())
                .commit();
    }

    @Override
    public void onProfileClick() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activityStartScreen, myProfileFragment)
                .commit();
    }

    @Override
    public void onNewsClick(int position) {
        startActivity(new Intent(this, NewsActivity.class));
    }
}