package com.example.tyzzer.android_projectweek1_googlebooks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Book {
    private String id, title, author, imageUrl, review;
    private Integer read;

    public Book(String title, String author, String imageUrl, String review, int read) {
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.review = review;
        this.read = read;
    }

    public Book(JSONObject inputJson) {

        try {
            this.id = inputJson.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = inputJson.getJSONObject("volumeInfo");

            try {
                this.title = json.getString("title");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                JSONArray authors = json.getJSONArray("authors");
                author = "";
                for (int i = 0; i < authors.length(); i++) {
                    if (i < (authors.length() - 1)) {
                        author += authors.getString(i) + ", ";
                    } else {
                        author += authors.getString(i);
                    }
                }
                this.author = author;
            } catch (JSONException e) {
                e.printStackTrace();
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
        this.read = 0;
    }

    public String toJsonString() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("title", this.title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("author", this.author);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("imageUrl", this.imageUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("review", this.review);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("read", this.read);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

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
}
