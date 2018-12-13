package com.thadocizn.bookapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thadocizn.bookapplication.classes.Book;
import com.thadocizn.bookapplication.classes.Bookshelf;

import java.util.ArrayList;

public class BookDbDao {

    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {
        if (db == null) {
            BookDbHelper helper = new BookDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static void createBook(Book book) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_READ_BOOK, book.isReadBook());

            int bookId = (int) db.insert(BookDbContract.BookEntry.TABLE_NAME_BOOK, null, values);

        }
    }

    public  static void createBookshelf(Bookshelf bookshelf){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOKSHELF_CATEGORY, bookshelf.getShelf_name());
            db.insert(BookDbContract.BookEntry.TABLE_NAME_BOOKSHELF, null, values);
        }
    }

    public Book getBook(String bookId) {

        int index;
        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                    BookDbContract.BookEntry.TABLE_NAME_BOOK,
                    BookDbContract.BookEntry.COLUMN_NAME_BOOK_TITLE,
                    bookId), null);

            if (cursor.moveToNext()) {
                Book book;

                book = getBookFromCursor(cursor);

                return book;
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();

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

    public static void updateBook(Book book) {
        if (db != null) {

            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.COLUMN_NAME_BOOK_KEY_ID,
                    book.getBookId());

            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.TABLE_NAME_BOOK, whereClause),
                    null);

            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
                values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
                values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
                values.put(BookDbContract.BookEntry.COLUMN_NAME_READ_BOOK, book.isReadBook());

                int rows = db.update(BookDbContract.BookEntry.TABLE_NAME_BOOK, values, whereClause, null);
            }

            cursor.close();
        }
    }

    public static void deleteBook(Book book) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.COLUMN_BOOK_ID,
                    book.getBookId());

            int rows = db.delete(BookDbContract.BookEntry.TABLE_NAME_BOOK, whereClause, null);
        }
    }

    private static Book getBookFromCursor(Cursor cursor) {
        int index;
        Book book;

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_NAME_BOOK_TITLE);
        String title = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_NAME_BOOK_IMAGE_URL);
        String imageUrl = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_NAME_BOOK_REVIEW);
        String bookReview = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_NAME_READ_BOOK);
        int readBook = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_NAME_BOOK_KEY_ID);
        String idBook = cursor.getString(index);

        book = new Book();
        book.setBookTitle(title);
        book.setBookImageUrl(imageUrl);
        book.setBookReview(bookReview);
        book.setReadBook(readBook);
        book.setBookId(idBook);
        return book;
    }
}
