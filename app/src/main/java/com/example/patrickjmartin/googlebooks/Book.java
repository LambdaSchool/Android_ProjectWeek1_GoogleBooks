package com.example.patrickjmartin.googlebooks;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Book implements Parcelable {

    private String title, author, review, publishDate, googleBooksID, image, bookshelfHomes;
    private int isRead;
    private boolean isFavorite, isSelected;

    public Book(String title, String author, String review, String publishDate, String googleBooksID, String image) {
        this.title = title;
        this.author = author;
        this.review = review;
        this.publishDate = publishDate;
        this.googleBooksID = googleBooksID;
        this.image = image.replace("http:", "https:");


    }

    public Book (JSONObject json) {

        try {
            JSONObject volumeInfo = json.getJSONObject("volumeInfo");
            this.title = volumeInfo.getString("title");
            try {
                JSONArray authorsJsonArray = volumeInfo.getJSONArray("authors");
                this.author = parseAuthors(authorsJsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
                this.author = "";
            }
            this.review = "";
            this.publishDate = volumeInfo.optString("publishedDate");
            this.googleBooksID = json.optString("id");
            this.image = volumeInfo.getJSONObject("imageLinks").optString("thumbnail");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.bookshelfHomes = "";
        this.isRead = 0;
        this.isFavorite = false;
        this.isSelected = false;
        if(this.image != null && !this.image.isEmpty()) {
            this.image = this.image
                    .replace("http:", "https:")
                    .replace("zoom=0", "zoom=1");

        }

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
        isFavorite = in.readByte() != 0;
        isSelected = in.readByte() != 0;

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


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean selected) {
        isFavorite = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

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

        if (this.bookshelfHomes == "") this.bookshelfHomes = bookshelfHomes;
        else this.bookshelfHomes += ("," + bookshelfHomes);
    }

    public void removeBookShelfHomes(String bookshelfToBeRemoved) {

        String compare = this.bookshelfHomes;
        this.bookshelfHomes.replaceAll("," + bookshelfToBeRemoved, "");

        if(compare == this.bookshelfHomes) {
            this.bookshelfHomes.replaceAll(bookshelfToBeRemoved + ",", "");
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
        dest.writeByte((byte)(isFavorite ? 1 : 0));
        dest.writeByte((byte)(isSelected ? 1 : 0));

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


    @Override
    public String toString() {

        String bookInfo = String.format(
                "Title: %s\n" +
                        "Author: %s\n" +
                        "Review: %s\n" +
                        "Publish Date: %s\n" +
                        "GoogleBooks ID: %s\n" +
                        "Bookshelve(s): %s\n",
                title,
                author,
                review,
                publishDate,
                googleBooksID,
                bookshelfHomes
        );

        return bookInfo;
    }
}
