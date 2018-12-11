package com.thadocizn.bookapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.thadocizn.bookapplication.classes.Book;

public class BookDbDao {

    private static SQLiteDatabase db;

    public static void initializeInstance(Context context){
        if (db == null){
            BookDbHelper helper = new BookDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static long createBook(Book book, long[] tagIds){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_READ_BOOK, book.isReadBook());

            long bookId = db.insert(BookDbContract.BookEntry.TABLE_NAME_BOOK, null, values);

            for (long tag_id: tagIds) {
                createBookTag(bookId, tag_id);
            }
            return bookId;
        }
        return Long.parseLong(null);
    }

    private static void createBookTag(long bookId, long tag_id) {
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_ID, bookId);
            values.put(BookDbContract.BookEntry.COLUMN_NAME_TAG_ID, tag_id);

            long id = db.insert(BookDbContract.BookEntry.TABLE_NAME_BOOKSHELF, null, values);
        }
    }
}
