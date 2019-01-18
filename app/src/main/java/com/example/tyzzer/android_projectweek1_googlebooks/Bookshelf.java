package com.example.tyzzer.android_projectweek1_googlebooks;

import java.util.ArrayList;

public class Bookshelf {
    private String name;
    int id;
    private ArrayList<Book> books;

    public Bookshelf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
