package com.example.tyzzer.android_projectweek1_googlebooks;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Book implements Parcelable {
    private String id, title, author, imageUrl, review;
    private int read;
    private boolean selected;

    public Book(String title, String author, String imageUrl, String review, int read) {
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.review = review;
        this.read = read;
    }

    public Book(JSONObject jsonObject) {

        try {
            this.id = jsonObject.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject json = jsonObject.getJSONObject("volumeInfo");

            try {
                this.title = json.getString("title");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                JSONArray authorsJsonArray = json.getJSONArray("authors");
                this.author = parseAuthors(authorsJsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
                this.author = "";
            }
            try {
                JSONObject links = json.getJSONObject("imageLinks");
                this.imageUrl = links.getString("smallThumbnail");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.review = "";
        this.selected = false;
        this.read = 0;
    }
    private static String parseAuthors(JSONArray authorsAry) {

        StringBuilder authorsString = new StringBuilder();

        for (int i = 0; i < authorsAry.length(); i++) {
            try {
                if (i == 0) {
                    authorsString.append(authorsAry.getString(i));
                } else {
                    authorsString.append(" - ").append(authorsAry.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return authorsString.toString();
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        review = in.readString();
        imageUrl = in.readString();
        read = in.readInt();
        selected = in.readByte() != 0;
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
        dest.writeString(imageUrl);
        dest.writeInt(read);
        dest.writeByte((byte)(selected ? 1 : 0));

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int isRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
