package com.example.jacob.android_projectweek1_googlebooks;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FirebaseDao {

    static final String PUBLIC_ID = "zfZcE0ajzpdKBtnbpcZKj1MdaJg2";

    public static String getUser() {
        String fbaseUser;
        try {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            fbaseUser = currentUser.getUid();
        } catch (NullPointerException e) {
            fbaseUser = PUBLIC_ID;
        }
        return fbaseUser;
    }


    public static void MonitorBooks() {
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(getUser()).child(Constants.FIREBASE_BOOKS);
        ValueEventListener dbListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bookshelf bookshelf = dataSnapshot.getValue(Bookshelf.class);
                BookshelfDbDao.updateBookshelf(bookshelf);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        reference.addValueEventListener(dbListener);
    }

    public static void createBook(Book book) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(getUser()).child(Constants.FIREBASE_BOOKS);
        reference.child(book.getId()).setValue(book);
    }

    public static void deleteBook(String id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(getUser()).child(Constants.FIREBASE_BOOKS);
        reference.child(id).removeValue();
    }

    public static void createBookshelf(Bookshelf bookshelf) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(getUser()).child(Constants.FIREBASE_BOOKSHELVES);
        reference.child(String.valueOf(bookshelf.getId())).setValue(bookshelf);
    }

    public static void deleteBookshelf(int bookshelfId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(getUser()).child(Constants.FIREBASE_BOOKSHELVES);
        reference.child(String.valueOf(bookshelfId)).removeValue();
    }

    public static void updateBookshelf(Bookshelf bookshelf) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(getUser()).child(Constants.FIREBASE_BOOKSHELVES).child(String.valueOf(bookshelf.getId()));
        reference.child("books").setValue(bookshelf.getBooks());
    }
}
