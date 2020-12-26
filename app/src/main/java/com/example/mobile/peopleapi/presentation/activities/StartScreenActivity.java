package com.example.mobile.peopleapi.presentation.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.callback.MyNetworkChangeCallback;
import com.example.mobile.peopleapi.presentation.fragments.MyProfileFragment;
import com.example.mobile.peopleapi.presentation.fragments.StartScreenFragment;
import com.example.mobile.peopleapi.presentation.helpers.AlertManager;
import com.example.mobile.peopleapi.presentation.listener.NetworkChangeListener;
import com.example.mobile.peopleapi.presentation.listener.OnNewsListener;
import com.example.mobile.peopleapi.presentation.listener.OnProfileListener;
import com.google.firebase.messaging.FirebaseMessaging;

import timber.log.Timber;

public class StartScreenActivity extends AppCompatActivity implements OnNewsListener, OnProfileListener {
    private MyProfileFragment myProfileFragment;
    private final AlertManager alertManager = new AlertManager(this);
    private final MyNetworkChangeCallback myNetworkChangeCallback = new MyNetworkChangeCallback(alertManager);
    private NetworkChangeListener networkChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        initFirebaseMessaging();
        networkChangeListener = new NetworkChangeListener(this, myNetworkChangeCallback);
        networkChangeListener.subscribe();

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

    private void initFirebaseMessaging() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Timber.e(task.getException());
                        return;
                    }
                    // Get new FCM registration token
                    String token = task.getResult();
                    Timber.i(token);
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkChangeListener.unsubscribe();
    }
}