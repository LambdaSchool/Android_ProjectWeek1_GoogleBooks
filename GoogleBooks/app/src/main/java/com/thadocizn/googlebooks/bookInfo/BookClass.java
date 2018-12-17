package com.thadocizn.googlebooks.bookInfo;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class BookClass implements Parcelable {
    private String bookId;
    private String bookTitle;
    private String bookImageUrl;
    private String bookReview;
    private int readBook;

    public BookClass(String bookId,
                String bookTitle,
                String bookImageUrl,
                String bookReview,
                boolean readBook) {
        this.bookId       = bookId;
        this.bookTitle    = bookTitle;
        this.bookImageUrl = bookImageUrl;
        this.bookReview   = bookReview;
        if (readBook){
            this.readBook = 0;
        }else {
            this.readBook = 1;
        }

    }

    protected BookClass(Parcel in) {
        bookId            = in.readString();
        bookTitle         = in.readString();
        bookImageUrl      = in.readString();
        bookReview        = in.readString();
        readBook          = in.readInt();
    }

    public static final Creator<BookClass> CREATOR = new Creator<BookClass>() {
        @Override
        public BookClass createFromParcel(Parcel in) {
            return new BookClass(in);
        }

        @Override
        public BookClass[] newArray(int size) {
            return new BookClass[size];
        }
    };

    public BookClass(String strId, String strTitle, String strReview, boolean b) {
        this.bookId = strId;
        this.bookTitle = strTitle;
        this.bookReview = strReview;
        if (b){
            this.readBook = 0;
        }else {
            this.readBook = 1;
        }
    }

    public BookClass() {

    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
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

    public BookClass(JSONObject jsonObject){

        try {
            this.bookId = jsonObject.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject json = jsonObject.getJSONObject("volumeInfo");
            this.bookTitle = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject json = jsonObject.getJSONObject("volumeInfo");
            JSONObject imageLink = json.getJSONObject("imageLinks");
            this.bookImageUrl = imageLink.getString("thumbnail");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookId);
        dest.writeString(bookTitle);
        dest.writeString(bookImageUrl);
        dest.writeString(bookReview);
        dest.writeInt(isReadBook());
    }
}
