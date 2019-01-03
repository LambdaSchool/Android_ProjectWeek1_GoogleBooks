package com.thadocizn.googlebooks.sqlObjects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.thadocizn.googlebooks.bookInfo.BookClass;
import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;

import java.util.ArrayList;

public class SqlDbDao {

    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {
        if (db == null) {
            BookDbHelper helper = new BookDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static void createBookshelf(String bookshelf) {

        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME, bookshelf);
            long bookshelfId = db.insert(BookDbContract.BookEntry.BOOKSHELF_TABLE_NAME, null, values);

        }
    }

    public static Bookshelf getBookshelf(Bookshelf id) {
        Bookshelf bookshelf = null;
        if (db != null) {

            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_ID,
                    id.getBookshelfId());
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.BOOKSHELF_TABLE_NAME, whereClause),
                    null);


            if (cursor.moveToFirst()) {
                bookshelf = getBookshelfFromCursor(cursor);
            } else {
                bookshelf = null;
            }
            cursor.close();
        }
        return bookshelf;
    }

    public static ArrayList<Bookshelf> getBookshelves() {
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();

        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s",
                    BookDbContract.BookEntry.BOOKSHELF_TABLE_NAME), null);

            while (cursor.moveToNext()) {
                bookshelves.add(getBookshelfFromCursor(cursor));
            }
            cursor.close();
        }
        return bookshelves;

    }

    private static Bookshelf getBookshelfFromCursor(Cursor cursor) {

        int index;

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_ID);
        int bookshelf_key = (int) cursor.getLong(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME);
        String name = cursor.getString(index);

        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setBookshelfId(bookshelf_key);
        bookshelf.setName(name);

        return bookshelf;

    }

    public static void updateBookshelfName(Bookshelf bookshelf) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME,
                    bookshelf.getName());
            long row = db.insert(BookDbContract.BookEntry.BOOKSHELF_TABLE_NAME, null, values);

        }
    }

    public static void deleteBookshelf(Bookshelf bookshelf) {
        if (db != null) {

            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME,
                    bookshelf.getName());

            int row = db.delete(BookDbContract.BookEntry.BOOKSHELF_TABLE_NAME, whereClause, null);

        }
    }

    public static void createBook(BookClass book) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_ID, book.getBookId());
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_READ_BOOK, book.isReadBook());

            long bookId = db.insert(BookDbContract.BookEntry.TABLE_NAME_BOOK, null, values);
            Log.i("This", "is" + bookId);
        }
    }

    public static BookClass getBook(BookClass bookId) {
        BookClass book = null;
        if (db != null) {

            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE,
                    bookId.getBookTitle());
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.TABLE_NAME_BOOK, whereClause),
                    null);


            if (cursor.moveToFirst()) {
                book = getBookFromCursor(cursor);
            } else {
                book = null;
            }
            cursor.close();
        }
        return book;
    }

    public static void updateBook(BookClass book) {
        if (db != null) {

            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE,
                    book.getBookTitle());

            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.TABLE_NAME_BOOK, whereClause),
                    null);

            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
                values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
                values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
                values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_CATEGORY, book.getCategory());
                values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_READ_BOOK, book.isReadBook());

                int rows = db.update(BookDbContract.BookEntry.TABLE_NAME_BOOK, values, whereClause, null);
            }

            cursor.close();
        }
    }

    public static void deleteBook(BookClass book) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE,
                    book.getBookTitle());

            int rows = db.delete(BookDbContract.BookEntry.TABLE_NAME_BOOK, whereClause, null);
        }
    }

    public static ArrayList<BookClass> getAllBooks() {
        ArrayList<BookClass> books = new ArrayList<>();

        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s",
                    BookDbContract.BookEntry.TABLE_NAME_BOOK), null);

            while (cursor.moveToNext()) {
                books.add(getBookFromCursor(cursor));
            }
            if (cursor != null) {
                cursor.close();
            }
        }

        return books;
    }

    private static BookClass getBookFromCursor(Cursor cursor) {
        int index;
        BookClass book;

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_KEY);
        int id = (int) cursor.getLong(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_ID);
        String bookId = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE);
        String title = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_IMAGE_URL);
        String imageUrl = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_REVIEW);
        String bookReview = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_CATEGORY);
        String category = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_READ_BOOK);
        int readBook = cursor.getInt(index);

        book = new BookClass();
        book.setBookKeyId(id);
        book.setBookId(bookId);
        book.setBookTitle(title);
        book.setBookImageUrl(imageUrl);
        book.setBookReview(bookReview);
        book.setCategory(category);
        book.setReadBook(readBook);
        return book;
    }

}
