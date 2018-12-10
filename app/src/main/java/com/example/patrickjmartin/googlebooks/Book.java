package com.example.patrickjmartin.googlebooks;

import android.graphics.Bitmap;

import org.json.JSONObject;

public class Book {

    private String title, review;
    private Bitmap cover;
    private Boolean isRead;

    public Book(String title, String review, Bitmap cover, Boolean isRead) {
        this.title = title;
        this.review = review;
        this.cover = cover;
        this.isRead = isRead;
    }

    public Book(JSONObject o) {
        //Do Some things.
    }

    public String getTitle() {
        return title;
    }

    public String getReview() {
        return review;
    }

    public Bitmap getCover() {
        return cover;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
