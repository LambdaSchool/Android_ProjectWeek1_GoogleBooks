package com.example.jacob.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class BooksDbDao {

    private static SQLiteDatabase db;

    static void initializeInstance(Context context) {
        if (db == null) {
            BooksDbHelper helper = new BooksDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    static void addBook(Book book) {
        if (db != null) {
            ContentValues values = getContentValues(book);
            db.insert(BooksDbContract.BookEntry.TABLE_NAME, null, values);
        }
    }

    @NonNull
    private static ContentValues getContentValues(Book book) {
        ContentValues values = new ContentValues();
        values.put(BooksDbContract.BookEntry._ID, book.getId());
        values.put(BooksDbContract.BookEntry.COLUMN_NAME_TITLE, book.getTitle());
        values.put(BooksDbContract.BookEntry.COLUMN_NAME_AUTHOR, book.getAuthor());
        values.put(BooksDbContract.BookEntry.COLUMN_NAME_PUBLISHED_DATE, book.getPublishedDate());
        values.put(BooksDbContract.BookEntry.COLUMN_NAME_IMAGE_URL, book.getImageUrl());
        values.put(BooksDbContract.BookEntry.COLUMN_NAME_REVIEW, book.getReview());
        values.put(BooksDbContract.BookEntry.COLUMN_NAME_HAS_BEEN_READ, (book.getHasBeenRead() ? 1 : 0));
        values.put(BooksDbContract.BookEntry.COLUMN_NAME_BOOKSHELVES, book.getBookshelves().toString());
        return values;
    }


    static Book readBook(String id) {
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                    BooksDbContract.BookEntry.TABLE_NAME,
                    BooksDbContract.BookEntry._ID,
                    id),
                    null);
            Book book = null;
            if (cursor.moveToNext() && (cursor.getCount() == 1)) {
                book = getBookFromCursor(cursor);
            }
            cursor.close();
            return book;
        } else {
            return null;
        }
    }

    static void updateBook(Book book) {
        if (db != null) {
            String whereClause = String.format("%s = %s", BooksDbContract.BookEntry._ID, book.getId());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BooksDbContract.BookEntry.TABLE_NAME,
                    whereClause),
                    null);
            if (cursor.getCount() == 1) {
                ContentValues values = getContentValues(book);
                db.update(BooksDbContract.BookEntry.TABLE_NAME, values, whereClause, null);
            }
            cursor.close();
        }
    }


    static void deleteBook(String id) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    BooksDbContract.BookEntry._ID,
                    id);
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BooksDbContract.BookEntry.TABLE_NAME,
                    whereClause),
                    null);
            if (cursor.getCount() == 1) {
                db.delete(BooksDbContract.BookEntry.TABLE_NAME, whereClause, null);
            }
            cursor.close();
        }
    }

    static ArrayList<Book> readAllBooks() {
        ArrayList<Book> books = new ArrayList<>();

        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s",
                    BooksDbContract.BookEntry.TABLE_NAME), null);
            while (cursor.moveToNext()) {
                books.add(getBookFromCursor(cursor));
            }
            cursor.close();
        }
        return books;
    }


    static Bookshelf readBookshelf(String id) {
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();
        ArrayList<Book> books = readAllBooks();
        ArrayList<String> bookshelfIds = new ArrayList<String>();
        for (Book book : books) {
            if (book.getBookshelves().contains(id)) {
                bookshelfIds.add(book.getId());
            }
        }

        return null;
    }


    private static Book getBookFromCursor(Cursor cursor) {
        int index;
        Book book = null;
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry._ID);
        book.setId(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_TITLE);
        book.setTitle(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_AUTHOR);
        book.setAuthor(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_PUBLISHED_DATE);
        book.setPublishedDate(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_IMAGE_URL);
        book.setImageUrl(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_REVIEW);
        book.setReview(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_HAS_BEEN_READ);
        book.setHasBeenRead(cursor.getInt(index) != 0);
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_BOOKSHELVES);
        book.setBookshelves(new ArrayList<String>(Arrays.asList(cursor.getString(index).split(","))));
        return book;
    }
}
