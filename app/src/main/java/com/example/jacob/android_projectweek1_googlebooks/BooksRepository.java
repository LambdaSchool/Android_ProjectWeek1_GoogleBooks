package com.example.jacob.android_projectweek1_googlebooks;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BooksRepository {

    public MutableLiveData<ArrayList<Book>> getBooks(int bookshelfId) {
        MutableLiveData<ArrayList<Book>> liveDataList = new MutableLiveData<>();
        Bookshelf bookshelf = BookshelfDbDao.readBookshelf(bookshelfId);
        ArrayList<Book> books = bookshelf.getBooks();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_BOOKSHELVES).child(String.valueOf(bookshelf.getId())).child("books");
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(FirebaseDao.getUser()).child(Constants.FIREBASE_BOOKSHELVES).child(String.valueOf(bookshelf.getId()));
                ValueEventListener dbListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
//                        ArrayList<String> bookIds = dataSnapshot.getValue(t);

                        Bookshelf fBookshelf;
                        ArrayList<Book> books = new ArrayList<>();
                        fBookshelf = dataSnapshot.getValue(Bookshelf.class);
                        books = fBookshelf.getBooks();
                        if (books !=null) {
                            liveDataList.postValue(books);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                reference.addListenerForSingleValueEvent(dbListener);
            }
        }).start();

        liveDataList.setValue(books);
        return liveDataList;
    }

    public static ArrayList<Book> addBook(int bookshelfId, Book book) {
        BookshelfDbDao.addBooktoBookshelf(bookshelfId, book);
        return BookshelfDbDao.readBookshelf(bookshelfId).getBooks();
    }

    public static ArrayList<Book> removeBook(int bookshelfId, String bookId) {
        BookshelfDbDao.removeBookfromBookshelf(bookshelfId, bookId);
        return BookshelfDbDao.readBookshelf(bookshelfId).getBooks();
    }

    public static void deleteBook(String bookId) {
        BooksDbDao.deleteBook(bookId);
    }
}
