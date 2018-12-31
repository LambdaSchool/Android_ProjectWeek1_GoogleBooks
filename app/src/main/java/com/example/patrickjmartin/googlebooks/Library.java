package com.example.patrickjmartin.googlebooks;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Library implements Serializable {

    private static volatile Library INSTANCE  ;
    private Map<String, ArrayList<Book>> bookShelf;


    private Library() {
        if(INSTANCE != null) {
            throw new RuntimeException("Use getINSTANCE()");
        } else {
            bookShelf = new HashMap<>();
            bookShelf.put("All Books", new ArrayList<>());
            bookShelf.put("Reviewed", new ArrayList<>());
            bookShelf.put("Read", new ArrayList<>());
            bookShelf.put("Favorites", new ArrayList<>());
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
        bookShelf.computeIfAbsent(shelfName, k-> new ArrayList<>()).add(toBeAdded);
        toBeAdded.setBookshelfHomes(shelfName);
    }

    public void removeFromBookshelf(String shelfName, Book toBeRemoved) {
        bookShelf.computeIfPresent(shelfName, (k, v)-> v.remove(toBeRemoved) && v.isEmpty()
                ? null : v );
        toBeRemoved.removeBookShelfHomes(shelfName);

    }



}
