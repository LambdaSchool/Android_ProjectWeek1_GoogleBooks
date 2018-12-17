package com.thadocizn.googlebooks.sqlObjects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public static void addBookToBookshelf(Bookshelf bookshelf, BookClass book) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.BOOKS_BOOKSHELF_COLUMN_NAME_BOOK_ID, book.getBookId());
            values.put(BookDbContract.BookEntry.BOOKS_BOOKSHELF_COLUMN_NAME_BOOkSHELF_ID, bookshelf.getName());
            long row = db.insert(BookDbContract.BookEntry.BOOKS_BOOKSHELF_TABLE, null, values);
        }
    }

    public static void createBookshelf(String bookshelf) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME, bookshelf);
            long i = db.insert(BookDbContract.BookEntry.BOOKSHELF_TABLE_NAME, null, values);
        }
    }

    public static ArrayList<Bookshelf> getBookshelves() {
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();

        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s;",
                    BookDbContract.BookEntry.BOOKS_BOOKSHELF_TABLE), null);

            while (cursor.moveToNext()) {
                bookshelves.add(getBookshelf(cursor));
            }
            cursor.close();
            return bookshelves;
        } else {
            return new ArrayList<>();
        }
    }

    public static Bookshelf getBookshelf(Cursor cursor) {

        int index;
        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME);
        String name = cursor.getString(index);

        return new Bookshelf(name);

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
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
            values.put(BookDbContract.BookEntry.BOOK_COLUMN_NAME_READ_BOOK, book.isReadBook());

            int bookId = (int) db.insert(BookDbContract.BookEntry.TABLE_NAME_BOOK, null, values);

        }
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
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s;",
                    BookDbContract.BookEntry.TABLE_NAME_BOOK), null);

            while (cursor.moveToNext()) {
                books.add(getBookFromCursor(cursor));
            }
            cursor.close();
            return books;
        } else {
            return new ArrayList<>();
        }

    }

    private static BookClass getBookFromCursor(Cursor cursor) {
        int index;
        BookClass book;

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE);
        String title = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_IMAGE_URL);
        String imageUrl = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_REVIEW);
        String bookReview = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_READ_BOOK);
        int readBook = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.BOOK_COLUMN_NAME_BOOK_ID);
        String idBook = cursor.getString(index);

        book = new BookClass();
        book.setBookTitle(title);
        book.setBookImageUrl(imageUrl);
        book.setBookReview(bookReview);
        book.setReadBook(readBook);
        book.setBookId(idBook);
        return book;
    }

}
