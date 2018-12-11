package com.example.jacob.android_projectweek1_googlebooks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Book {
    private String id, title, author, publishedDate, imageUrl, cachedFileLocation;

    public Book(String id, String title, String author, String publishedDate, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.imageUrl = imageUrl;
    }

    public Book(JSONObject json) {
        try {
            this.id = json.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            } catch(JSONException e){
                e.printStackTrace();
            }
            try {
                this.publishedDate = json.getString("publishedDate");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                JSONObject links = json.getJSONObject("imageLinks");
                this.imageUrl = links.getString("smallThumbnail");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        public String getId () {
            return id;
        }

        public void setId (String id){
            this.id = id;
        }

        public String getTitle () {
            return title;
        }

        public void setTitle (String title){
            this.title = title;
        }

        public String getAuthor () {
            return author;
        }

        public void setAuthor (String author){
            this.author = author;
        }

        public String getPublishedDate () {
            return publishedDate;
        }

        public void setPublishedDate (String publishedDate){
            this.publishedDate = publishedDate;
        }

        public String getImageUrl () {
            return imageUrl;
        }

        public void setImageUrl (String imageUrl){
            this.imageUrl = imageUrl;
        }
    }
