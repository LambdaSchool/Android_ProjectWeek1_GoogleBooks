package com.example.jacob.android_projectweek1_googlebooks;

import java.util.ArrayList;

public class Bookshelf {
    private String id, title;
    private ArrayList<Book> books;

    public Bookshelf(String id, String title, ArrayList<Book> books) {
        this.id = id;
        this.title = title;
        this.books = books;
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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
