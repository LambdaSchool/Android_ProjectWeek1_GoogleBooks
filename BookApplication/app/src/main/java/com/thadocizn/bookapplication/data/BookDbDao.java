package com.thadocizn.bookapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thadocizn.bookapplication.classes.Book;

import java.util.ArrayList;

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

    public Book getBook(long bookId){

        int index;
        Cursor cursor = null;

        if (db != null){
                cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                        BookDbContract.BookEntry.TABLE_NAME_BOOK,
                        BookDbContract.BookEntry.COLUMN_NAME_BOOK_ID,
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

        index = cursor.getColumnIndexOrThrow(BookDbContract.BookEntry.COLUMN_NAME_BOOK_ID);
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
