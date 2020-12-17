package com.example.mobile.peopleapi.presentation.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile.R;
import com.google.android.material.textfield.TextInputEditText;

public class MyProfileActivity extends AppCompatActivity {

    private TextInputEditText changeName;
    private Button changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        changeName = findViewById(R.id.profile_change_input);
        changeButton = findViewById(R.id.profile_change_button);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyProfileActivity.this, "Name was chanched", Toast.LENGTH_SHORT).show();
                changeName.setText("");
            }
        });
    }
}