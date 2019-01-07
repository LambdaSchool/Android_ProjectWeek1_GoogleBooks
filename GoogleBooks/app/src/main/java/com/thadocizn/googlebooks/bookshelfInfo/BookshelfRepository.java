package com.thadocizn.googlebooks.bookshelfInfo;

import android.arch.lifecycle.MutableLiveData;

import com.thadocizn.googlebooks.sqlObjects.SqlDbDao;

import java.util.ArrayList;

public class BookshelfRepository {

    ArrayList<Bookshelf> bookshelves;
    MutableLiveData<ArrayList<Bookshelf>> liveData;

    public MutableLiveData<ArrayList<Bookshelf>> getBookShelves(){
        liveData = new MutableLiveData<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                bookshelves = SqlDbDao.getBookshelves();
                liveData.postValue(bookshelves);
            }
        }).start();

        return liveData;
    }

    public ArrayList<Bookshelf> getList(){
        ArrayList<Bookshelf> list = new ArrayList<>();
        list = SqlDbDao.getBookshelves();
        return list;
    }


    public void createBookshelf(final String bookshelf) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                SqlDbDao.createBookshelf(bookshelf);
            }
        }).start();

    }


    public void deleteBookshelf(final Bookshelf bookshelf) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.deleteBookshelf(bookshelf);
            }
        }).start();
    }

    public void updateBookshelf(final Bookshelf bookshelf) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.updateBookshelfName(bookshelf);
            }
        }).start();
    }
}
