package com.example.jacob.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import java.util.ArrayList;


public class BooksDbDao {

    static SQLiteDatabase db;

    static void initializeInstance(Context context) {
        if (db == null) {
            BooksDbHelper helper = new BooksDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    static void addBook(Book book) {
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                    BooksDbContract.BookEntry.BOOK_TABLE_NAME,
                    BooksDbContract.BookEntry._ID,
                    book.getId()),
                    null);
            if (cursor.getCount() == 0) {
                ContentValues values = getContentValues(book);
                db.insert(BooksDbContract.BookEntry.BOOK_TABLE_NAME, null, values);
                FirebaseDao.createBook(book);
                BookshelfDbDao.addBooktoBookshelf(Constants.DEFAULT_BOOKSHELVES[0],book);
            }
            cursor.close();
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
        return values;
    }


    static Book readBook(String id) {
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                    BooksDbContract.BookEntry.BOOK_TABLE_NAME,
                    BooksDbContract.BookEntry._ID,
                    id),
                    null);
            Book book = null;
//            int temp = cursor.getCount();
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
            String whereClause = String.format("%s = '%s'", BooksDbContract.BookEntry._ID, book.getId());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BooksDbContract.BookEntry.BOOK_TABLE_NAME,
                    whereClause),
                    null);
            if (cursor.getCount() == 1) {
                ContentValues values = getContentValues(book);
                db.update(BooksDbContract.BookEntry.BOOK_TABLE_NAME, values, whereClause, null);
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
                    BooksDbContract.BookEntry.BOOK_TABLE_NAME,
                    whereClause),
                    null);
            if (cursor.getCount() == 1) {
                db.delete(BooksDbContract.BookEntry.BOOK_TABLE_NAME, whereClause, null);
                FirebaseDao.deleteBook(id);
            }
            cursor.close();
        }
    }

    static ArrayList<Book> readAllBooks() {
        ArrayList<Book> books = new ArrayList<>();

        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s",
                    BooksDbContract.BookEntry.BOOK_TABLE_NAME), null);
            while (cursor.moveToNext()) {
                books.add(getBookFromCursor(cursor));
            }
            cursor.close();
        }
        return books;
    }

    private static Book getBookFromCursor(Cursor cursor) {
        int index;
        Book book = new Book(null, null, null, null, null, null, false);
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
        return book;
    }
}
