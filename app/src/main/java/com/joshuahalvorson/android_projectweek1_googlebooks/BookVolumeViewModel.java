package com.joshuahalvorson.android_projectweek1_googlebooks;

import java.util.ArrayList;

public class BookVolumeViewModel {
    private static BookVolumeRepo repo = new BookVolumeRepo();;
    public static void addBook(BookVolume bookVolume){
        repo.addBook(bookVolume);
    }

    public static ArrayList<BookVolume> readBooks(){
        return repo.readBooks();
    }
}
