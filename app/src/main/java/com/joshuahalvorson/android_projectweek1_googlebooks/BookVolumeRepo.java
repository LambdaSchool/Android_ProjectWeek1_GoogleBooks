package com.joshuahalvorson.android_projectweek1_googlebooks;

import java.util.ArrayList;

public class BookVolumeRepo {
    ArrayList<BookVolume> booksList;
    public void addBook(final BookVolume bookVolume){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BooksDbDao.createBookEntry(bookVolume);
            }
        }).start();
    }

    public ArrayList<BookVolume> readBooks(){
        booksList = BooksDbDao.readAllBookEntries();
        return booksList;
    }

    public void updateBook(BookVolume bookVolume){
        BooksDbDao.updateBookEntry(bookVolume);
    }

    public void deleteBook(BookVolume bookVolume){
        BooksDbDao.deleteBookEntry(bookVolume);
    }
}
