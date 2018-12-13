package com.example.jacob.android_projectweek1_googlebooks;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class BookshelvesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Bookshelf>> bookshelfList;
    private BookshelvesRepository repo;

    public LiveData<ArrayList<Bookshelf>> getBookshelvesList() {
        if(bookshelfList == null) {
            loadList();
        }
        return bookshelfList;
    }

    private void loadList() {
        repo = new BookshelvesRepository();
        bookshelfList = repo.getBookshelves();
    }

    public void addBookshelf(String bookshelfName) {
        if(bookshelfList != null) {
            bookshelfList.setValue(repo.addBookshelf(bookshelfName));
        }
    }

    public void deleteBookshelf(int bookshelfId) {
        if(bookshelfList != null) {
            bookshelfList.setValue(repo.deleteBookshelf(bookshelfId));
        }
    }
}
