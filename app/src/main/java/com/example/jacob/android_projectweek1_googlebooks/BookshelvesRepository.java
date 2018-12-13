package com.example.jacob.android_projectweek1_googlebooks;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class BookshelvesRepository {

    public MutableLiveData<ArrayList<Bookshelf>> getBookshelves() {
        MutableLiveData<ArrayList<Bookshelf>> liveDataList = new MutableLiveData<>();
        liveDataList.setValue(BookshelfDbDao.readAllBookshelves());
        return liveDataList;
    }

    public static ArrayList<Bookshelf> addBookshelf(String bookshelfName) {
        BookshelfDbDao.addBookshelf(bookshelfName);
        return BookshelfDbDao.readAllBookshelves();
    }

    public static ArrayList<Bookshelf> deleteBookshelf(int bookshelfId) {
        BookshelfDbDao.deleteBookshelf(bookshelfId);
        return BookshelfDbDao.readAllBookshelves();
    }
}
