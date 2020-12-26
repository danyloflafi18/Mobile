package com.example.mobile.peopleapi.presentation.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.listener.ISignIn;
import com.example.mobile.peopleapi.presentation.listener.ISignUp;
import com.example.mobile.peopleapi.presentation.listener.OnLanguageListener;
import com.example.mobile.peopleapi.presentation.fragments.SignInFragment;
import com.example.mobile.peopleapi.presentation.fragments.SignUpFragment;
import com.example.mobile.peopleapi.presentation.helpers.LanguageManager;
import com.example.mobile.peopleapi.presentation.helpers.SharedPrefManager;
import com.example.mobile.peopleapi.presentation.shared_pref.SharedPrefs;

public class MainActivity extends AppCompatActivity implements SignUpFragment.ISignInPage,
        SignInFragment.ISignUpPage, ISignUp, ISignIn, OnLanguageListener {

    public static final String DE_LANGUAGE = "de";
    public static final String EN_LANGUAGE = "en";
    private SignUpFragment signUpFragment;
    private SignInFragment signInFragment;
    private final LanguageManager languageManager = new LanguageManager();
    private final Configuration configuration = new Configuration();
    private final SharedPrefManager sharedPrefManager = new SharedPrefManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpFragment = new SignUpFragment();
        signInFragment = new SignInFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, signInFragment)
                .commit();
    }

    @Override
    public void onSignInClicked() {
        startActivity(new Intent(this, StartScreenActivity.class));
    }

    @Override
    public void onSignUpClicked() {
        startActivity(new Intent(this, StartScreenActivity.class));
    }

    @Override
    public void onSignUpPageClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, signUpFragment)
                .commit();
    }

    @Override
    public void onSignInPageClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, signInFragment)
                .commit();
    }

    @Override
    public void onLanguageClick() {
        SharedPrefs sharedPrefs = sharedPrefManager.initSharedPrefs(this);
        String language = sharedPrefs.getLanguage();

        if (language.equals(DE_LANGUAGE)) {
            sharedPrefs.setLanguage(EN_LANGUAGE);
            getResources().updateConfiguration(configuration, null);
        } else if (language.equals(EN_LANGUAGE)) {
            sharedPrefs.setLanguage(DE_LANGUAGE);
            languageManager.setLanguage(DE_LANGUAGE, configuration, this);
        }

        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}