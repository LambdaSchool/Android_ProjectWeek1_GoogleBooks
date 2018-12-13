package com.example.jacob.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class BookshelfDbDao extends BooksDbDao {

    static void addBookshelf(Bookshelf bookshelf) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BooksDbContract.BookEntry.COLUMN_NAME_TITLE, bookshelf.getTitle());
            values.put(BooksDbContract.BookEntry.COLUMN_NAME_BOOK_IDS, bookshelf.getBooks().toString());
            long resultId = db.insert(BooksDbContract.BookEntry.BOOKSHELF_TABLE_NAME, null, values);
        }
    }

    static ArrayList<Bookshelf> readAllBookshelves() {
        ArrayList<Bookshelf> bookshelves = new ArrayList<>();
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s",
                    BooksDbContract.BookEntry.BOOKSHELF_TABLE_NAME), null);
            int index;
            while (cursor.moveToNext()) {
                bookshelves.add(getBookshelfFromCursor(cursor));
            }
            cursor.close();
        }
        return bookshelves;
    }

    static Bookshelf readBookshelf(int id) {
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                    BooksDbContract.BookEntry.BOOKSHELF_TABLE_NAME,
                    BooksDbContract.BookEntry._ID,
                    id),
                    null);
            Bookshelf bookshelf = null;
            if (cursor.moveToNext() && (cursor.getCount() == 1)) {
                bookshelf = getBookshelfFromCursor(cursor);
            }
            cursor.close();
            return bookshelf;
        } else {
            return null;
        }

    }


    static void deleteBookshelf(int id) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    BooksDbContract.BookEntry._ID,
                    id);
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BooksDbContract.BookEntry.BOOKSHELF_TABLE_NAME,
                    whereClause),
                    null);
            if (cursor.getCount() == 1) {
                db.delete(BooksDbContract.BookEntry.BOOKSHELF_TABLE_NAME, whereClause, null);
            }
            cursor.close();
        }
    }

    private static Bookshelf getBookshelfFromCursor(Cursor cursor) {
        int index;
        Bookshelf bookshelf = new Bookshelf(-1, null, null);
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry._ID);
        bookshelf.setId(cursor.getInt(index));
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_TITLE);
        bookshelf.setTitle(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_BOOK_IDS);
        String[] listOfIds = cursor.getString(index).split(",");
        ArrayList<Book> books = new ArrayList<>();
        for (String id : listOfIds) {
            Book book = null;
            book = BooksDbDao.readBook(id);
            if (book != null) {
                books.add(book);
            }
        }
        bookshelf.setBooks(books);
        return bookshelf;
    }

}
