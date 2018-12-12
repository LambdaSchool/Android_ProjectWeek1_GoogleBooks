package com.example.jacob.android_projectweek1_googlebooks;

import java.util.ArrayList;

public class Bookshelf {
    private String title;
    int id;
    private ArrayList<Book> books;

    public Bookshelf(int id, String title, ArrayList<Book> books) {
        this.id = id;
        this.title = title;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
