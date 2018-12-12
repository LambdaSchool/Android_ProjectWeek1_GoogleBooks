package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

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
            values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_IS_FAVORITE, bookVolume.getIsFavorite());
            db.insert(BooksDbContract.BookEntry.BOOKS_TABLE_NAME, null, values);
        }
    }

    public static void createBookshelf(Bookshelf bookshelf){
        if(db != null){
            ContentValues values = new ContentValues();
            values.put(BooksDbContract.BookEntry.BOOKSHELVES_COLUMN_TITLE, bookshelf.getName());
            db.insert(BooksDbContract.BookEntry.BOOKSHELVES_TABLE_NAME, null, values);
        }
    }

    public static ArrayList<Bookshelf> readAllBookshelves(){
        if(db != null){
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s;", BooksDbContract.BookEntry.BOOKSHELVES_TABLE_NAME), null);
            ArrayList<Bookshelf> bookshelves = new ArrayList<>();
            while(cursor.moveToNext()){
                Bookshelf bookshelf = getBookshelfData(cursor);
                bookshelves.add(bookshelf);
            }
            cursor.close();
            return bookshelves;
        }else{
            return new ArrayList<>();
        }
    }

    public static ArrayList<BookVolume> readAllBookEntries(){
        if(db != null){
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s;", BooksDbContract.BookEntry.BOOKS_TABLE_NAME), null);
            ArrayList<BookVolume> booksList = new ArrayList<>();
            while(cursor.moveToNext()){
                BookVolume bookVolume = getBookVolumeData(cursor);
                booksList.add(bookVolume);
            }
            cursor.close();
            return booksList;
        }else{
            return new ArrayList<>();
        }
    }

    public static void updateBookEntryHasRead(BookVolume bookVolume){
        if (db != null) {
            String whereClause = String.format("%s = '%s'", BooksDbContract.BookEntry.BOOKS_COLUMN_TITLE,
                    bookVolume.getTitle());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                   BooksDbContract.BookEntry.BOOKS_TABLE_NAME, whereClause), null);
            if(cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_HAS_READ, bookVolume.isHasRead());
                db.update(BooksDbContract.BookEntry.BOOKS_TABLE_NAME, values, whereClause, null);
            }
        }
    }

    public static void updateBookEntryIsFavorite(BookVolume bookVolume){
        if (db != null) {
            String whereClause = String.format("%s = '%s'", BooksDbContract.BookEntry.BOOKS_COLUMN_TITLE,
                    bookVolume.getTitle());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                   BooksDbContract.BookEntry.BOOKS_TABLE_NAME, whereClause), null);
            if(cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_IS_FAVORITE, bookVolume.getIsFavorite());
                db.update(BooksDbContract.BookEntry.BOOKS_TABLE_NAME, values, whereClause, null);
            }
        }
    }

    public static void updateBookEntryReview(BookVolume bookVolume){
        if (db != null) {
            String whereClause = String.format("%s = '%s'", BooksDbContract.BookEntry.BOOKS_COLUMN_TITLE,
                    bookVolume.getTitle());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BooksDbContract.BookEntry.BOOKS_TABLE_NAME, whereClause), null);
            if(cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BooksDbContract.BookEntry.BOOKS_COLUMN_USER_REVIEW, bookVolume.getUserReview());
                db.update(BooksDbContract.BookEntry.BOOKS_TABLE_NAME, values, whereClause, null);
            }
        }
    }

    public static void deleteBookEntry(BookVolume bookVolume){
        if(db != null){
            String where = String.format("%s = '%s'", BooksDbContract.BookEntry.BOOKS_COLUMN_TITLE, bookVolume.getTitle());
            db.delete(BooksDbContract.BookEntry.BOOKS_TABLE_NAME, where, null);
        }
    }

    @NonNull
    private static BookVolume getBookVolumeData(Cursor cursor) {
        int index;
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
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKS_COLUMN_IS_FAVORITE);
        int isFavorite = cursor.getInt(index);
        return new BookVolume(title, imageUrl, userReview, authors, publishedDate, pages, hasRead, isFavorite);
    }

    private static Bookshelf getBookshelfData(Cursor cursor){
        int index;
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.BOOKSHELVES_COLUMN_TITLE);
        String name = cursor.getString(index);
        return new Bookshelf(name);
    }
}
