package com.thadocizn.googlebooks.bookshelfInfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class BookshelfViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Bookshelf>> shelfList;
    private BookshelfRepository repo;

    public LiveData<ArrayList<Bookshelf>> getBookshelfList(){
        if (shelfList == null){
            shelfList = new MutableLiveData<>();
            loadList();
        }

        return shelfList;
    }

    private void loadList() {
        repo      = new BookshelfRepository();
        shelfList = repo.getBookShelves();
    }

    public void addBookshelf(String bookshelf) {
        repo = new BookshelfRepository();
        repo.createBookshelf(bookshelf);
    }

    public ArrayList<Bookshelf> getBookshelfs(){
        repo = new BookshelfRepository();
        ArrayList<Bookshelf> list = new ArrayList<>(repo.getList());
        return list;
    }
    public void deleteBookshelf(Bookshelf bookshelf) {
        repo.deleteBookshelf(bookshelf);
    }

    public void updateBookshelf(Bookshelf bookshelf) {
        repo.updateBookshelf(bookshelf);
    }
}
