package com.thadocizn.googlebooks.bookshelfInfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.ArrayList;

public class BookshelfViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Bookshelf>> shelfList;
    private BookshelfRepository repo;

    public LiveData<ArrayList<Bookshelf>> getBookshelfList(Context context){
        if (shelfList == null){
            loadList(context);
        }

        return shelfList;
    }

    private void loadList(Context context) {
        repo = new BookshelfRepository();
        shelfList = repo.getBookShelves(context);
    }

    public void addBookshelf(String bookshelf) {
        repo = new BookshelfRepository();
        repo.createBookshelf(bookshelf);
    }

    public void deleteBookshelf(Bookshelf bookshelf) {
        repo.deleteBookshelf(bookshelf);
    }

    public void updateBookshelf(Bookshelf bookshelf) {
        repo.updateBookshelf(bookshelf);
    }
}
