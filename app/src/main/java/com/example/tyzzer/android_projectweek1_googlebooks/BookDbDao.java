package com.example.tyzzer.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;

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

    public static void updateBookRead(Book book){
        if (db != null) {
            String whereClause = String.format
                    ("%s = '%s'", BookDbContract.BookEntry.COLUMN_TITLE,
                            book.getTitle());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.BOOK_TABLE_NAME, whereClause),
                    null);
            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BookDbContract.BookEntry.COLUMN_READ, book.isRead());
                db.update(BookDbContract.BookEntry.BOOK_TABLE_NAME, values, whereClause,
                        null);
            }
            cursor.close();
        }
    }
    public static void updateBookReview(Book book){
        if (db != null) {
            String whereClause = String.format
                    ("%s = '%s'", BookDbContract.BookEntry.COLUMN_TITLE,
                            book.getTitle());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.BOOK_TABLE_NAME, whereClause),
                    null);
            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BookDbContract.BookEntry.COLUMN_REVIEW, book.getReview());
                db.update(BookDbContract.BookEntry.BOOK_TABLE_NAME, values, whereClause,
                        null);
            }
            cursor.close();
        }
    }

    public static ArrayList<Book> listBooks(){
        String getBooks = String.format("SELECT * FROM %s;", BookDbContract.BookEntry.BOOK_TABLE_NAME);
        Cursor cursor = db.rawQuery(getBooks, null);
        int index;
        ArrayList<Book> books = new ArrayList<>();
        while(cursor.moveToNext()){
            index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_TITLE);
            String title = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_AUTHOR);
            String author = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_IMAGE_URL);            index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_RELEASE_DATE);
            String imageUrl = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_REVIEW);
            String review = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_READ);
            int read = cursor.getInt(index);
            Book book = new Book(title, author, imageUrl, review, read);
            books.add(book);
        }
        cursor.close();
        return books;
    }

    public static void deleteBook(Book book){
        if(db != null){
            String where = String.format("%s = '%s'", BookDbContract.BookEntry.COLUMN_TITLE, book.getTitle());
            db.delete(BookDbContract.BookEntry.BOOK_TABLE_NAME, where, null);
        }
    }

    public static void addBookshelf(Bookshelf bookshelf) {
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM bookshelf WHERE shelf_name='%s'",
                    bookshelf.getName()),
                    null);

            if (cursor.getCount() == 0) {

                ContentValues values = new ContentValues();
                values.put(BookDbContract.BookEntry.COLUMN_SHELF_NAME, bookshelf.getName());
                db.insert(BookDbContract.BookEntry.SHELF_TABLE_NAME, null, values);
            }
        }
    }

    private static ArrayList<Bookshelf> listBookshelves(){
        String getBookshelf = String.format("SELECT * FROM %s;", BookDbContract.BookEntry.SHELF_TABLE_NAME);
        Cursor cursor = db.rawQuery(getBookshelf, null);
        int index;
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();
        while(cursor.moveToNext()){
            index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_SHELF_NAME);
            String name = cursor.getString(index);
            Bookshelf bookshelf = new Bookshelf(name);
            bookshelves.add(bookshelf);
        }
        cursor.close();
        return bookshelves;
    }

    public static void deleteBookshelf(Bookshelf bookshelf){
        if(db != null){
            String where = String.format("%s = '%s'",
                    BookDbContract.BookEntry.COLUMN_SHELF_NAME,
                    bookshelf.getName());
            db.delete(BookDbContract.BookEntry.SHELF_TABLE_NAME, where, null);
        }
    }

    public static void createBookinshelf(Bookshelf bookshelf, Book book){
        if(db != null){
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.COLUMN_SHELF_ID, bookshelf.getName());
            values.put(BookDbContract.BookEntry.COLUMN_BOOK_ID, book.getTitle());
            db.insert(BookDbContract.BookEntry.BOOKINSHELF_TABLE_NAME, null, values);
        }
    }

    public static void deleteBookinshelf(Bookshelf bookshelf, Book book){
        if(db != null){
            String where = String.format("%s = '%s' AND %s='%s'",
                    BookDbContract.BookEntry.COLUMN_SHELF_ID, bookshelf.getName(),
                    BookDbContract.BookEntry.COLUMN_BOOK_ID, book.getTitle());
            db.delete(BookDbContract.BookEntry.BOOKINSHELF_TABLE_NAME, where, null);
        }
    }
}
a