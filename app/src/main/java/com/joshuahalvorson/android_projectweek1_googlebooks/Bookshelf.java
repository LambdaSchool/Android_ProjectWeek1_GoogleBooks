package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.os.Parcel;
import android.os.Parcelable;

public class Bookshelf implements Parcelable {
    private String name;

    public Bookshelf(String name) {
        this.name = name;
    }

    protected Bookshelf(Parcel in) {
        name = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
