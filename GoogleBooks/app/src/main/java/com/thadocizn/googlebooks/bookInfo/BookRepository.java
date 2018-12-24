package com.thadocizn.googlebooks.bookInfo;

import android.arch.lifecycle.MutableLiveData;

import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;
import com.thadocizn.googlebooks.sqlObjects.SqlDbDao;

import java.util.ArrayList;

public class BookRepository {


    public void createBook(final BookClass book) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.createBook(book);
            }
        }).start();
    }

    public void deleteBook(final BookClass book) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.deleteBook(book);
            }
        }).start();
    }

    public void updateBook(final BookClass book) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.updateBook(book);
            }
        }).start();
    }

    public void addBookToBookshelf(final Bookshelf bookshelf, final BookClass book){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlDbDao.addBookToBookshelf(bookshelf.getBookshelfId(), book.getBookKeyId());
            }
        }).start();
    }
}
