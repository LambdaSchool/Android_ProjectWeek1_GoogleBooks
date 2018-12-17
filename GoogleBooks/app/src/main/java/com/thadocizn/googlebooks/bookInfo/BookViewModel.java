package com.thadocizn.googlebooks.bookInfo;

import android.arch.lifecycle.ViewModel;

import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;

import java.util.ArrayList;

public class BookViewModel extends ViewModel {

    private BookRepository repo;
    private ArrayList<BookClass> bookList;

        public void addBook(BookClass book){
            repo = new BookRepository();
            repo.createBook(book);
             repo.getBooks();
    }

    public void deleteBook(BookClass book){
            repo.deleteBook(book);
            repo.getBooks();
    }

    public void updateBook(BookClass book){
            repo.updateBook(book);
    }

    public void addBookshelf(String bookshelf){
            repo.createBookshelf(bookshelf);
    }

    public void deleteBookshelf(Bookshelf bookshelf){
            repo.deleteBookshelf(bookshelf);
    }

    public void updateBookshelf(Bookshelf bookshelf){
            repo.updateBookshelf(bookshelf);
    }
}
