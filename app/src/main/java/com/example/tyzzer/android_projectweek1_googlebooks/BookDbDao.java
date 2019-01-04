package com.example.tyzzer.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

public class BookDbDao {
    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {
        if (db == null) {
            BookDbHelper helper = new BookDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static void addBook(Book book) {
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM books WHERE title='%s'",
                    book.getTitle()),
                    null);


            if (cursor.getCount() == 0) {
                ContentValues values = new ContentValues();
                values.put(BookDbContract.BookEntry.COLUMN_TITLE, book.getTitle());
                values.put(BookDbContract.BookEntry.COLUMN_AUTHOR, book.getAuthor());
                values.put(BookDbContract.BookEntry.COLUMN_IMAGE_URL, book.getImageUrl());
                values.put(BookDbContract.BookEntry.COLUMN_REVIEW, book.getReview());
                values.put(BookDbContract.BookEntry.COLUMN_READ, book.isRead());
                db.insert(BookDbContract.BookEntry.BOOK_TABLE_NAME, null, values);
            }
            cursor.close();
        }
    }
}
