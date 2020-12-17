package com.example.mobile.peopleapi.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.example.mobile.peopleapi.domain.clickListener.ISignIn;
import com.example.mobile.peopleapi.domain.clickListener.ISignUp;
import com.example.mobile.peopleapi.domain.translate.LocaleManager;
import com.example.mobile.peopleapi.presentation.fragments.SignInFragment;
import com.example.mobile.peopleapi.presentation.fragments.SignUpFragment;

public class MainActivity extends AppCompatActivity implements SignUpFragment.ISignInPage,
        SignInFragment.ISignUpPage, ISignUp, ISignIn {

    private SignUpFragment signUpFragment;
    private SignInFragment signInFragment;
    private LocaleManager localeManager;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_translate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.local_english:
                setNewLocale(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNewLocale(AppCompatActivity context) {
        LocaleManager.setNewLocale(this, LocaleManager.ENGLISH);
        Intent intent = context.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}