package com.example.mobile.peopleapi.presentation.ui_data;

public class UserViewData {
    private String author;
    private String title;
    private String publishedAt;
    private String urlToImage;

    public UserViewData(String author, String title, String publishedAt, String urlToImage) {
        this.author = author;
        this.title = title;
        this.publishedAt = publishedAt;
        this.urlToImage = urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
