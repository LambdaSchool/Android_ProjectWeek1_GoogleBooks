package com.thadocizn.bookapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thadocizn.bookapplication.classes.Book;
import com.thadocizn.bookapplication.classes.Tag;

import java.util.ArrayList;

public class BookDbDao {

    private static SQLiteDatabase db;

    //TODO work on the table joins

    public static void initializeInstance(Context context){
        if (db == null){
            BookDbHelper helper = new BookDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static void createBook(Book book, int[] tagIds){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_TITLE, book.getBookTitle());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_IMAGE_URL, book.getBookImageUrl());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_REVIEW, book.getBookReview());
            values.put(BookDbContract.BookEntry.COLUMN_NAME_READ_BOOK, book.isReadBook());

            int bookId = (int) db.insert(BookDbContract.BookEntry.TABLE_NAME_BOOK, null, values);

            for (int tag_id: tagIds) {
                createBookTag(bookId, tag_id);
            }
        }
    }

    public static void createTag(Tag tag){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.COLUMN_NAME_TAG, tag.getTagName());
            db.insert(BookDbContract.BookEntry.TABLE_NAME_TAG, null, values);
        }
    }

    private static void createBookTag(int bookId, int tag_id) {
            if (db != null){
            ContentValues values = new ContentValues();
            values.put(BookDbContract.BookEntry.COLUMN_NAME_BOOK_KEY_ID, bookId);
            values.put(BookDbContract.BookEntry.COLUMN_NAME_TAG_KEY_ID, tag_id);

            long id = db.insert(BookDbContract.BookEntry.TABLE_NAME_BOOKSHELF, null, values);
        }
    }

    public Book getBook(int bookId){

        int index;
        Cursor cursor = null;

        if (db != null){
                cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                        BookDbContract.BookEntry.TABLE_NAME_BOOK,
                        BookDbContract.BookEntry.COLUMN_NAME_BOOK_KEY_ID,
                        bookId), null);

                if (cursor.moveToNext()){
                    Book book;

                    book = getBookFromCursor(cursor);

                    return book;
                }
        }

        if (cursor != null){
            cursor.close();
        }
        return null;
    }

    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> books = new ArrayList<>();

        if (db != null){
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s;",
                    BookDbContract.BookEntry.TABLE_NAME_BOOK),null);

            while (cursor.moveToNext()){
                books.add(getBookFromCursor(cursor));
            }
            cursor.close();
            return  books;
        }else {
            return new ArrayList<>();
        }

    }

    public ArrayList<Book>getBooksWithSameTag(Tag tag){

        ArrayList<Book> books = new ArrayList<>();
        if (db != null){
            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.COLUMN_TAG_ID,
                    tag.getTagId());
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.TABLE_NAME_BOOKSHELF,
                    whereClause),null);
            while (cursor.moveToNext()){
                books.add(getBookFromCursor(cursor));
            }
            cursor.close();
            return books;
        }else {
            return new ArrayList<>();
        }
    }

    public static void updateTag(Tag tag) {

        if (db != null){


            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.COLUMN_TAG_ID,
                    tag.getTagId());
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.TABLE_NAME_TAG, whereClause),
                    null);

            if (cursor.getCount() == 1){
                ContentValues values = new ContentValues();
                values.put(BookDbContract.BookEntry.COLUMN_NAME_TAG, tag.getTagName());
                int row = db.update(BookDbContract.BookEntry.TABLE_NAME_TAG, values, whereClause, null);
            }

        }
    }

    public static void updateBook(Book book){
        if (db != null){

            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.COLUMN_NAME_BOOK_KEY_ID,
                    book.getBookId());

            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BookDbContract.BookEntry.TABLE_NAME_BOOK, whereClause),
                    null);

            if (cursor.getCount() == 1){
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

    public static void  deleteBook(Book book){
        if (db != null){
            String whereClause = String.format("%s = '%s'",
                    BookDbContract.BookEntry.COLUMN_BOOK_ID,
                    book.getBookId());

            int rows = db .delete(BookDbContract.BookEntry.TABLE_NAME_BOOK, whereClause, null);
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
        int idBook = cursor.getInt(index);

        book = new Book();
        book.setBookTitle(title);
        book.setBookImageUrl(imageUrl);
        book.setBookReview(bookReview);
        book.setReadBook(readBook);
        book.setBookId(idBook);
         return book;
    }
}
