package com.example.mobile.peopleapi.presentation.mapper;

import com.example.mobile.peopleapi.domain.entity.Article;
import com.example.mobile.peopleapi.domain.entity.Example;
import com.example.mobile.peopleapi.presentation.ui_data.NewsViewData;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
public class NewsItemMapper implements Function<Example, NewsViewData> {
    private final String author;

    public NewsItemMapper(String author) {
        this.author = author;
    }

    @Override
    public NewsViewData apply(@NonNull Example newsData) throws Exception {
        NewsViewData newsViewData = null;
        for (Article item : newsData.getArticles()) {
            if (item.getAuthor().equals(author)) {
                newsViewData = new NewsViewData(
                        item.getAuthor(),
                        item.getTitle(),
                        item.getPublishedAt(),
                        item.getUrlToImage());
            }
        }
        return newsViewData;
    }
}
