package com.example.patrickjmartin.googlebooks;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Library implements Serializable {

    private static volatile Library INSTANCE  ;
    private HashMap bookShelf;


    private Library() {
        if(INSTANCE != null) {
            throw new RuntimeException("Use getINSTANCE()");
        } else {
            this.bookShelf = new HashMap<String, ArrayList>();
            this.bookShelf.put("All Books", new ArrayList<>());
            this.bookShelf.put("Read", new ArrayList<>());
            this.bookShelf.put("Favorites", new ArrayList<>());
        }

    }

    public static Library getINSTANCE() {
        if(INSTANCE == null) {
            synchronized (Library.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Library();

                }
            }
        }
        return  INSTANCE;
    }

    public void addToBookshelf(String shelfName, Book toBeAdded) {

    }


}
