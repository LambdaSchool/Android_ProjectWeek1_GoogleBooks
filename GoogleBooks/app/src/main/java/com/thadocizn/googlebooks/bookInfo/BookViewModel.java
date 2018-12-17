package com.thadocizn.googlebooks.bookInfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.ArrayList;

public class BookViewModel extends ViewModel {

    private BookRepository repo;
    private ArrayList<BookClass> bookList;

        public void addBook(BookClass book){
            repo.createBook(book);
             repo.getBooks();
    }

    public void deleteBook(BookClass book){
            repo.deleteBook(book);
            repo.getBooks();
    }

    public void updateBook(BookClass book){
            repo.updateBok(book);
    }
}
