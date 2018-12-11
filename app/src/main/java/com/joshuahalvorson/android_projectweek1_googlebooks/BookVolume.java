package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.os.Parcel;
import android.os.Parcelable;

public class BookVolume implements Parcelable {
    private String title, imageUrl, userReview, authors, publishedDate;
    private int pages;
    private int hasRead;

    public BookVolume(String title, String imageUrl, String userReview, String authors, String publishedDate, int pages, int hasRead) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.userReview = userReview;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.hasRead = hasRead;
    }

    protected BookVolume(Parcel in) {
        title = in.readString();
        imageUrl = in.readString();
        userReview = in.readString();
        authors = in.readString();
        publishedDate = in.readString();
        pages = in.readInt();
        hasRead = in.readInt();
    }

    public static final Creator<BookVolume> CREATOR = new Creator<BookVolume>() {
        @Override
        public BookVolume createFromParcel(Parcel in) {
            return new BookVolume(in);
        }

        @Override
        public BookVolume[] newArray(int size) {
            return new BookVolume[size];
        }
    };

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

    public int isHasRead() {
        return hasRead;
    }

    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(userReview);
        dest.writeString(authors);
        dest.writeString(publishedDate);
        dest.writeInt(pages);
        dest.writeInt(hasRead);
    }
}
