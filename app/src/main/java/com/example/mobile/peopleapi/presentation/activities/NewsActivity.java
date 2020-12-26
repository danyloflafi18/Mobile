package com.example.mobile.peopleapi.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mobile.R;

public class NewsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textDate;
    private TextView description;
    private TextView textAuthor;
    private String image;
    private String date;
    private String author;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initUi();
        getNews();
        setNews();
    }

    private void initUi() {
        description = findViewById(R.id.description);
        textAuthor = findViewById(R.id.author);
        textDate = findViewById(R.id.date);
        imageView = findViewById(R.id.image);
    }

    private void getNews() {
        Intent intent = getIntent();
        image = intent.getStringExtra("urlToImage");
        title = intent.getStringExtra("title");
        author = intent.getStringExtra("author");
        date = intent.getStringExtra("date");
    }

    private void setNews() {
        Glide.with(this)
                .load(image)
                .fitCenter()
                .into(imageView);
        textDate.setText(date);
        textAuthor.setText(author);
        description.setText(title);
    }
}