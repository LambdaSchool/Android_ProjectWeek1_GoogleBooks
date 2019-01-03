package com.example.patrickjmartin.googlebooks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Library implements Serializable {

    private static volatile Library INSTANCE  ;
    private Map<String, ArrayList<Book>> bookShelf;
    private ArrayList<String> libraryDirectory;


    private Library() {
        if(INSTANCE != null) {
            throw new RuntimeException("Use getINSTANCE()");
        } else {
            bookShelf = new HashMap<>();
            bookShelf.put("All Books", new ArrayList<>());
            bookShelf.put("Reviewed", new ArrayList<>());
            bookShelf.put("Read", new ArrayList<>());
            bookShelf.put("Favorites", new ArrayList<>());
            libraryDirectory = new ArrayList<>(bookShelf.keySet());
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
        shelfName = toCamelCase(shelfName);
        bookShelf.computeIfAbsent(shelfName, k-> new ArrayList<>()).add(toBeAdded);

    }

    public void addToBookshelf(String shelfName) {
        shelfName = toCamelCase(shelfName);
        bookShelf.computeIfAbsent(shelfName, k-> new ArrayList<>());
    }


    public void removeFromBookshelf(String shelfName, Book toBeRemoved) {
        shelfName = toCamelCase(shelfName);
        bookShelf.computeIfPresent(shelfName, (k, v)-> v.remove(toBeRemoved) && v.isEmpty()
                ? null : v );


    }

    public void removeFromBookshelf(String shelfName) {
        shelfName = toCamelCase(shelfName);

        if (!Objects.equals(shelfName, "All Books")) {
            bookShelf.remove(shelfName);
            libraryDirectory.remove(shelfName);
        }
    }

    public static String toCamelCase(String initString) {
        String newString = "";
        if (initString == null || initString == "" ) {
            return newString;
        } else {
            String[] initAry = initString.split(" ");
            for (int i = 0; i < initAry.length; i++) {
                String first = initAry[i].substring(0,1).toUpperCase();
                String rest = initAry[i].substring(1).toLowerCase();

                if (i == 0) newString += (first + rest);
                else newString += (" " + first + rest);
            }
        }
        return newString;
    }




    public ArrayList getBookshelfNames() {
        return new ArrayList<String>(bookShelf.keySet());
    }



}
