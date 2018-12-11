package com.thadocizn.bookapplication.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private int bookId;
    private String  bookTitle;
    private String  bookImageUrl;
    private String  bookReview;
    private int readBook;

    public Book(String bookTitle, String bookImageUrl, String bookReview, int bookId) {
        this.bookTitle = bookTitle;
        this.bookImageUrl = bookImageUrl;
        this.bookReview = bookReview;
        this.bookId = bookId;
    }

    protected Book(Parcel in) {
        bookTitle = in.readString();
        bookImageUrl = in.readString();
        bookReview = in.readString();
        readBook = in.readInt();
        bookId = in.readInt();
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

    public Book() {

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

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

    public int isReadBook() {
        return readBook;
    }

    public void setReadBook(int readBook) {
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
        dest.writeInt(readBook);
        dest.writeInt(bookId);
    }

}
