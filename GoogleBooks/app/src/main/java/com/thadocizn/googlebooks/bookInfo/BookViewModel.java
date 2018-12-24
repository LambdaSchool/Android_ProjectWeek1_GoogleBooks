package com.thadocizn.googlebooks.bookInfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;

import java.util.ArrayList;

public class BookViewModel extends ViewModel {
    private BookRepository repo;

    public void addBook(BookClass book) {
        repo = new BookRepository();
        repo.createBook(book);
    }

    public void deleteBook(BookClass book) {
        repo.deleteBook(book);
    }

    public void updateBook(BookClass book) {
        repo.updateBook(book);
    }


}
