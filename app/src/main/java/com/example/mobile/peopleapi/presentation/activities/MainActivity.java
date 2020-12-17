package com.example.mobile.peopleapi.presentation.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.peopleapi.domain.clickListener.ISignIn;
import com.example.mobile.peopleapi.domain.clickListener.ISignUp;
import com.example.mobile.peopleapi.presentation.fragments.SignInFragment;
import com.example.mobile.peopleapi.presentation.fragments.SignUpFragment;

public class MainActivity extends AppCompatActivity implements SignUpFragment.ISignInPage,
        SignInFragment.ISignUpPage, ISignUp, ISignIn {

    private SignUpFragment signUpFragment;
    private SignInFragment signInFragment;

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
}