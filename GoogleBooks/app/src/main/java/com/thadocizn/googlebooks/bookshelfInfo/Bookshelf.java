package com.thadocizn.googlebooks.bookshelfInfo;

import android.os.Parcel;
import android.os.Parcelable;

public class Bookshelf implements Parcelable {

    private String name;
    private long bookshelfId;

    public Bookshelf() {
        this.name        = "";
        this.bookshelfId = 0;
    }

    protected Bookshelf(Parcel in) {
        name        = in.readString();
        bookshelfId = in.readLong();
    }

    public static final Creator<Bookshelf> CREATOR = new Creator<Bookshelf>() {
        @Override
        public Bookshelf createFromParcel(Parcel in) {
            return new Bookshelf(in);
        }

        @Override
        public Bookshelf[] newArray(int size) {
            return new Bookshelf[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBookshelfId() {
        return bookshelfId;
    }

    public void setBookshelfId(long bookshelfId) {
        this.bookshelfId = bookshelfId;
    }

    public Bookshelf(long bookshelfId, String name) {

        this.name        = name;
        this.bookshelfId = bookshelfId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(bookshelfId);
    }
}
