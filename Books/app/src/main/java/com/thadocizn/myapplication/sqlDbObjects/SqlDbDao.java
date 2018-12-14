package com.thadocizn.myapplication.sqlDbObjects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thadocizn.myapplication.bookInfo.Book;
import com.thadocizn.myapplication.bookshelfInfo.Bookshelf;

import java.util.ArrayList;

import static com.thadocizn.myapplication.sqlDbObjects.BookDbContract.*;

public class SqlDbDao {

    private static SQLiteDatabase db;

    public static void initializeInstance(Context context){
        if (db == null){
            BookDbHelper helper = new BookDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static void addBookToBookshelf(Bookshelf bookshelf, Book book){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookEntry.BOOKS_BOOKSHELF_COLUMN_NAME_BOOK_ID, book.getBookTitle());
            values.put(BookEntry.BOOKS_BOOKSHELF_COLUMN_NAME_BOOkSHELF_ID, bookshelf.getName());
           long row = db.insert(BookEntry.BOOKS_BOOKSHELF_TABLE, null, values);
        }
    }

    public  static void createBookshelf(String bookshelf){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME, bookshelf);
            long i = db.insert(BookEntry.BOOKSHELF_TABLE_NAME, null, values);
        }
    }

    public static Bookshelf getBookshelf(Cursor cursor){

        int index;
        index = cursor.getColumnIndexOrThrow(BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME);
        String name = cursor.getString(index);

        return new Bookshelf(name);

    }
    public static void updateBookshelfName(Bookshelf bookshelf){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME,
                    bookshelf.getName());
            long row = db.insert(BookEntry.BOOKSHELF_TABLE_NAME, null, values);

        }
    }

    public static void deleteBookshelf(Bookshelf bookshelf){
        if(db != null){

                    String whereClause = String.format("%s = '%s'",
                            BookEntry.BOOKSHELF_COLUMN_NAME_SHELF_NAME,
                            bookshelf.getName());

                    int row = db.delete(BookEntry.BOOKSHELF_TABLE_NAME, whereClause, null);

            }
        }

    public static void createBook(Book book){
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
            values.put(BookEntry.BOOK_COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
            values.put(BookEntry.BOOK_COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
            values.put(BookEntry.BOOK_COLUMN_NAME_READ_BOOK, book.isReadBook());

            int bookId = (int) db.insert(BookEntry.TABLE_NAME_BOOK, null, values);

        }
    }

    public static void updateBook(Book book) {
        if (db != null) {

            String whereClause = String.format("%s = '%s'",
                    BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE,
                    book.getBookTitle());

            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookEntry.TABLE_NAME_BOOK, whereClause),
                    null);

            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
                values.put(BookEntry.BOOK_COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
                values.put(BookEntry.BOOK_COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
                values.put(BookEntry.BOOK_COLUMN_NAME_READ_BOOK, book.isReadBook());

                int rows = db.update(BookEntry.TABLE_NAME_BOOK, values, whereClause, null);
            }

            cursor.close();
        }
    }

    public static void deleteBook(Book book) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE,
                    book.getBookTitle());

            int rows = db.delete(BookEntry.TABLE_NAME_BOOK, whereClause, null);
        }
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();

        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s;",
                    BookEntry.TABLE_NAME_BOOK), null);

            while (cursor.moveToNext()) {
                books.add(getBookFromCursor(cursor));
            }
            cursor.close();
            return books;
        } else {
            return new ArrayList<>();
        }

    }

    private static Book getBookFromCursor(Cursor cursor) {
        int index;
        Book book;

        index = cursor.getColumnIndexOrThrow(BookEntry.BOOK_COLUMN_NAME_BOOK_TITLE);
        String title = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookEntry.BOOK_COLUMN_NAME_BOOK_IMAGE_URL);
        String imageUrl = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookEntry.BOOK_COLUMN_NAME_BOOK_REVIEW);
        String bookReview = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(BookEntry.BOOK_COLUMN_NAME_READ_BOOK);
        int readBook = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(BookEntry.BOOK_COLUMN_NAME_BOOK_ID);
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
