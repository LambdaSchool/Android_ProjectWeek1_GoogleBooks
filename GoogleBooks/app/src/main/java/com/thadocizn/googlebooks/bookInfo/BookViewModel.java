package com.thadocizn.googlebooks.bookInfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.ArrayList;

public class BookViewModel extends ViewModel {

    private BookRepository repo;

    private MutableLiveData<ArrayList<BookClass>> bookList;

    public LiveData<ArrayList<BookClass>> getBookList(Context context){
        if (bookList == null){
            loadList(context);
        }

        return bookList;
    }

    public void loadList(Context context){
        repo = new BookRepository();
        bookList = repo.getBooks();
    }

    public void addBook(BookClass book){
        if (bookList != null){
            repo.createBook(book);
            bookList = repo.getBooks();
        }
    }

    public void deleteBook(BookClass book){
        if (bookList != null){
            repo.deleteBook(book);
            bookList = repo.getBooks();
        }
    }

    public void updateBook(BookClass book){
        if (bookList != null){
            repo.updateBok(book);
            bookList = repo.getBooks();
        }
    }
}
