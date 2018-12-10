package com.thadocizn.bookapplication.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private String  bookTitle;
    private String  bookImageUrl;
    private String  bookReview;
    private boolean readBook;

    public Book(String bookTitle, String bookImageUrl, String bookReview) {
        this.bookTitle = bookTitle;
        this.bookImageUrl = bookImageUrl;
        this.bookReview = bookReview;
        this.readBook = false;
    }

    protected Book(Parcel in) {
        bookTitle = in.readString();
        bookImageUrl = in.readString();
        bookReview = in.readString();
        readBook = in.readByte() != 0;
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

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public String getBookReview() {
        return bookReview;
    }

    public void setBookReview(String bookReview) {
        this.bookReview = bookReview;
    }

    public boolean isReadBook() {
        return readBook;
    }

    public void setReadBook(boolean readBook) {
        this.readBook = readBook;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookTitle);
        dest.writeString(bookImageUrl);
        dest.writeString(bookReview);
        dest.writeByte((byte) (readBook ? 1 : 0));
    }

}
