package com.thadocizn.googlebooks.bookInfo;

import android.arch.lifecycle.MutableLiveData;

import com.thadocizn.googlebooks.sqlObjects.SqlDbDao;

import java.util.ArrayList;

public class BookRepository {

    public MutableLiveData<ArrayList<BookClass>> getBooks() {
        final MutableLiveData<ArrayList<BookClass>> liveDataList = new MutableLiveData<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<BookClass> books = SqlDbDao.getAllBooks();
                liveDataList.postValue(books);
            }
        }).start();
        return liveDataList;
    }

    public void createBook(final BookClass book) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.createBook(book);
            }
        }).start();
    }

    public void deleteBook(final BookClass book){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.deleteBook(book);
            }
        }).start();
    }

    public void updateBok(final BookClass book){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.updateBook(book);
            }
        }).start();
    }
}
