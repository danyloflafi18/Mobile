package com.example.mobile.peopleapi.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.viewModel.NewsItemViewModel;
import com.example.mobile.peopleapi.presentation.viewModel.NewsItemViewModelFactory;

import timber.log.Timber;

public class NotificationActivity extends AppCompatActivity {
    private NewsItemViewModel newsItemViewModel;
    private final String AUTHOR = "AUTHOR";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        String author = getIntent().getExtras().getString(AUTHOR);
        initViewModel(author);
    }

    private void initViewModel(String author) {
        newsItemViewModel = new ViewModelProvider(this, new NewsItemViewModelFactory())
                .get(NewsItemViewModel.class);;

        newsItemViewModel.getNewsItem().observe(this, newsItem -> {
            Timber.e(newsItem.getAuthor());
            Intent intent = new Intent(this, NewsActivity.class);
            Context context = null;
            context.startActivity(intent);
        });
        newsItemViewModel.loadSpecificNewsItem(author);
    }
}