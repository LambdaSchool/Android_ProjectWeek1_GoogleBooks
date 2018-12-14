package com.example.jacob.android_projectweek1_googlebooks;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class BookshelfViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Book>> bookList;
    private BooksRepository repo;
    private int bookshelfId;

    public LiveData<ArrayList<Book>> getBooksList(int bookshelfId) {
        if (bookList == null) {
            loadList(bookshelfId);
        }
        return bookList;
    }

    private void loadList(int bookshelfId) {
        repo = new BooksRepository();
        bookList = repo.getBooks(bookshelfId);
    }


    public void removeBook(int bookshelfId, String bookId) {
        if (bookList != null) {
            bookList.setValue(repo.removeBook(bookshelfId, bookId));
        }
    }

    public void deleteBook(String bookId) {
        repo.deleteBook(bookId);
    }

}
