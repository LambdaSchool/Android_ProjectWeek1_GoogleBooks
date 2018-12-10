package com.joshuahalvorson.android_projectweek1_googlebooks;

public class BookVolume {
    private String title, imageUrl, userReview, authors, publishedDate;
    private int pages;
    private boolean hasRead;

    public BookVolume(String title, String imageUrl, String userReview, String authors, String publishedDate, int pages, boolean hasRead) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.userReview = userReview;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.hasRead = hasRead;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }
}
