package com.example.jacob.android_projectweek1_googlebooks;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class BooksRepository {

    public MutableLiveData<ArrayList<Book>> getBooks(int bookshelfId) {
        MutableLiveData<ArrayList<Book>> liveDataList = new MutableLiveData<>();
        Bookshelf bookshelf = BookshelfDbDao.readBookshelf(bookshelfId);
        ArrayList<Book> books = bookshelf.getBooks();
        liveDataList.setValue(books);
        return liveDataList;
    }

    public static ArrayList<Book> addBook(int bookshelfId, Book book) {
        BookshelfDbDao.addBooktoBookshelf(bookshelfId, book);
        return BookshelfDbDao.readBookshelf(bookshelfId).getBooks();
    }

    public static ArrayList<Book> deleteBook(int bookshelfId, String bookId) {
        BookshelfDbDao.removeBookfromBookshelf(bookshelfId,bookId);
        return BookshelfDbDao.readBookshelf(bookshelfId).getBooks();
    }
}
