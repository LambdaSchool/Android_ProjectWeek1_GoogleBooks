package com.thadocizn.googlebooks.bookInfo;

import android.arch.lifecycle.MutableLiveData;

import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;
import com.thadocizn.googlebooks.sqlObjects.SqlDbDao;

import java.util.ArrayList;

public class BookRepository {


    public static MutableLiveData<ArrayList<BookClass>> getBook() {
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

    public ArrayList<Bookshelf> getBookshelves(){
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();
        bookshelves = SqlDbDao.getBookshelves();

        return bookshelves;
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
}
