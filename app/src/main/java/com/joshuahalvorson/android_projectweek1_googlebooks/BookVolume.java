package com.joshuahalvorson.android_projectweek1_googlebooks;

public class BookVolume {
    private String title, imageUrl, userReview;
    private boolean hasRead;

    public BookVolume(String title, String imageUrl, String userReview, boolean hasRead) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.userReview = userReview;
        this.hasRead = hasRead;
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
