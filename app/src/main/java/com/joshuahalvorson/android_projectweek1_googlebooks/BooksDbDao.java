package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BooksDbDao {
    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {
        if (db == null) {
            BooksDbHelper helper = new BooksDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static void createBookEntry(BookVolume bookVolume){
        if(db != null){
            ContentValues values = new ContentValues();
            values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_TITLE, bookVolume.getTitle());
            values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_IMAGE_URL, bookVolume.getImageUrl());
            values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_USER_REVIEW, bookVolume.getUserReview());
            values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_AUTHORS, bookVolume.getAuthors());
            values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_PUBLISHED_DATE, bookVolume.getPublishedDate());
            values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_PAGES, bookVolume.getPages());
            values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_HAS_READ, bookVolume.isHasRead());
            long resultId = db.insert(BooksDbContract.BookEntry.BOOKS_TABLE_NAME, null, values);
        }
    }

    public static ArrayList<BookVolume> readAllBookEntries(){
        if(db != null){
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s;", BooksDbContract.BookEntry.BOOKS_TABLE_NAME), null);
            ArrayList<BookVolume> booksList = new ArrayList<>();
            int index;
            while(cursor.moveToNext()){
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKS_COLUMN_TITLE);
                String title = cursor.getString(index);
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKS_COLUMN_IMAGE_URL);
                String imageUrl = cursor.getString(index);
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKS_COLUMN_USER_REVIEW);
                String userReview = cursor.getString(index);
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKS_COLUMN_AUTHORS);
                String authors = cursor.getString(index);
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKS_COLUMN_PUBLISHED_DATE);
                String publishedDate = cursor.getString(index);
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKS_COLUMN_PAGES);
                int pages = cursor.getInt(index);
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKS_COLUMN_HAS_READ);
                int hasRead = cursor.getInt(index);
                BookVolume bookVolume = new BookVolume(title, imageUrl, userReview, authors, publishedDate, pages, hasRead);
                booksList.add(bookVolume);
            }
            cursor.close();
            return booksList;
        }else{
            return new ArrayList<>();
        }
    }
}
