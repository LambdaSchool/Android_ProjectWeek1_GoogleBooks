package com.example.jacob.android_projectweek1_googlebooks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FirebaseDao {

    public static void MonitorBooks() {
        FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_BOOKS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void createBook(Book book) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Constants.FIREBASE_BOOKS);
        reference.child(book.getId()).setValue(book);
    }

    public static void deleteBook(Book book) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Constants.FIREBASE_BOOKS);
        reference.child(book.getId()).removeValue();
    }

    public static void deleteBook(String id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Constants.FIREBASE_BOOKS);
        reference.child(id).removeValue();
    }

    public static void createBookshelf(Bookshelf bookshelf) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Constants.FIREBASE_BOOKSHELVES);
        reference.child(String.valueOf(bookshelf.getId())).setValue(bookshelf);
    }

    public static void deleteBookshelf(Bookshelf bookshelf) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Constants.FIREBASE_BOOKSHELVES);
        reference.child(String.valueOf(bookshelf.getId())).removeValue();
    }

    public static void deleteBookshelf(int bookshelfId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Constants.FIREBASE_BOOKSHELVES);
        reference.child(String.valueOf(bookshelfId)).removeValue();
    }

    public static void updateBookshelf(Bookshelf bookshelf) {
        //I'm sure there's a better way to do this than deleting the whole thing and then re-creating it.
        deleteBookshelf(bookshelf);
        createBookshelf(bookshelf);
    }

    public ArrayList<Bookshelf> getBookshelves() {
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Constants.FIREBASE_BOOKSHELVES);
        reference.getDatabase();

        return bookshelves;
    }
}
