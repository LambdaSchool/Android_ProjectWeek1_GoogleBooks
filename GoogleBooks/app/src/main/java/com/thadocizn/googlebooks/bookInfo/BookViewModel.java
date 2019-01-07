package com.thadocizn.googlebooks.bookInfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class BookViewModel extends ViewModel {

    private MutableLiveData<ArrayList<BookClass>> bookList;
    private BookRepository repo;

    public LiveData<ArrayList<BookClass>> getBookList(){
        if (bookList == null){
            bookList = new MutableLiveData<>();
            loadList();
        }

        return bookList;
    }

    private void loadList() {
        repo     = new BookRepository();
        bookList = repo.getBookList();
    }

    public BookClass getBook(BookClass bookId){
        BookClass book;
        repo = new BookRepository();
        book = repo.getBook(bookId);
        return book;
    }


    public void addBook(BookClass book) {
        repo = new BookRepository();
        repo.createBook(book);
    }

    public void deleteBook(BookClass book) {
        repo = new BookRepository();
        repo.deleteBook(book);
    }

    public void updateBook(BookClass book) {
        repo = new BookRepository();
        repo.updateBook(book);
    }


}
