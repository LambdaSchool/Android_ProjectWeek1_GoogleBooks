package com.example.patrickjmartin.googlebooks;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;


public class Book implements Parcelable {

    private String title, author, review, publishDate, googleBooksID, image, bookshelfHomes;
    private int isRead;

    public Book(String title, String author, String review, String publishDate, String googleBooksID, String image) {
        this.title = title;
        this.author = author;
        this.review = review;
        this.publishDate = publishDate;
        this.googleBooksID = googleBooksID;
        this.image = image;

    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        review = in.readString();
        publishDate = in.readString();
        googleBooksID = in.readString();
        image = in.readString();
        bookshelfHomes = in.readString();
        isRead = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public void setReview(String review) {
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getGoogleBooksID() {
        return googleBooksID;
    }

    public String getImage() {
        return image;
    }

    public String getBookshelfHomes() {
        return bookshelfHomes;
    }

    public int getRead() {
        return isRead;
    }

    public void setBookshelfHomes(String bookshelfHomes) {

        if (this.bookshelfHomes == null) {
            this.bookshelfHomes = bookshelfHomes;
        } else {
            this.bookshelfHomes += bookshelfHomes;
        }
    }

    public void setRead(int read) {
        isRead = read;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(review);
        dest.writeString(publishDate);
        dest.writeString(googleBooksID);
        dest.writeString(image);
        dest.writeString(bookshelfHomes);
        dest.writeInt(isRead);
    }
}
