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
import java.util.Objects;

public class BookshelvesRepository {
    MutableLiveData<ArrayList<Bookshelf>> liveDataList;

    public MutableLiveData<ArrayList<Bookshelf>> getBookshelves() {
        liveDataList = new MutableLiveData<>();
        liveDataList.setValue(BookshelfDbDao.readAllBookshelves());

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_BOOKS);
                ValueEventListener dbListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<Book> books = new ArrayList<>();

                        for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                            Book book = dataValues.getValue(Book.class);
                            if (book != null) {
                                BooksDbDao.addBook(book);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                reference.addListenerForSingleValueEvent(dbListener);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_BOOKSHELVES);
                ValueEventListener dbListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<Bookshelf> bookshelves = new ArrayList<>();

                        for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                            Bookshelf bookshelf = dataValues.getValue(Bookshelf.class);
                            if (bookshelf != null) {
                                bookshelves.add(bookshelf);
                                BookshelfDbDao.addBookshelf(bookshelf);
                            }
                        }
//                        GenericTypeIndicator<ArrayList<Bookshelf>> t = new GenericTypeIndicator<ArrayList<Bookshelf>>() {
//                        };
//                        bookshelves = dataSnapshot.getValue(t);
//                        bookshelves.removeIf(Objects::isNull);
//                        for (Bookshelf bookshelf : bookshelves) {
//                            BookshelfDbDao.addBookshelf(bookshelf.getTitle());
//                        }
                        int tempInt = BookshelfDbDao.readAllBookshelves().size();
                        int temp2Int = Constants.DEFAULT_BOOKSHELVES.length;
                        Boolean temp3 = (tempInt < temp2Int);

                        if (BookshelfDbDao.readAllBookshelves().size() < Constants.DEFAULT_BOOKSHELVES.length) {
                            //Create 2 default tables
                            for (String shelfTitle : Constants.DEFAULT_BOOKSHELVES) {
                                BookshelfDbDao.addBookshelf(shelfTitle);
                            }
                            bookshelves = BookshelfDbDao.readAllBookshelves();
                        }

                        liveDataList.postValue(bookshelves);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                reference.addListenerForSingleValueEvent(dbListener);
            }
        }).start();

        return liveDataList;
    }

    public static ArrayList<Bookshelf> addBookshelf(String bookshelfName) {
        BookshelfDbDao.addBookshelf(bookshelfName);
        return BookshelfDbDao.readAllBookshelves();
    }

    public static ArrayList<Bookshelf> deleteBookshelf(int bookshelfId) {
        BookshelfDbDao.deleteBookshelf(bookshelfId);
        return BookshelfDbDao.readAllBookshelves();
    }
}
