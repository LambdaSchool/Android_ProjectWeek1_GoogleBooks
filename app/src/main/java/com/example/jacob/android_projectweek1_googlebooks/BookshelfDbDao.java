package com.example.jacob.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class BookshelfDbDao extends BooksDbDao {

    static void addBookshelf(String title) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BooksDbContract.BookEntry.COLUMN_NAME_TITLE, title);
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

    static void updateBookshelf(Bookshelf bookshelf) {
        if (db != null) {
            String whereClause = String.format("%s = %s", BooksDbContract.BookEntry._ID, bookshelf.getId());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    BooksDbContract.BookEntry.BOOKSHELF_TABLE_NAME,
                    whereClause),
                    null);
            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(BooksDbContract.BookEntry._ID, bookshelf.getId());
                values.put(BooksDbContract.BookEntry.COLUMN_NAME_TITLE, bookshelf.getTitle());
                ArrayList<Book> books = bookshelf.getBooks();
                ArrayList<String> bookIds = new ArrayList<>();
                for (Book book : books) {
                    bookIds.add(book.getId());
                }
                String idsToString = bookIds.toString();
                idsToString = idsToString.substring(1,idsToString.length()-1);
                values.put(BooksDbContract.BookEntry.COLUMN_NAME_BOOK_IDS, idsToString);
                db.update(BooksDbContract.BookEntry.BOOKSHELF_TABLE_NAME, values, whereClause, null);
            }
            cursor.close();
        }
    }

    static void addBooktoBookshelf(String bookshelfTitle, Book book) {
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                    BooksDbContract.BookEntry.BOOKSHELF_TABLE_NAME,
                    BooksDbContract.BookEntry.COLUMN_NAME_TITLE,
                    bookshelfTitle),
                    null);
            Bookshelf bookshelf = null;
            if (cursor.moveToNext() && (cursor.getCount() == 1)) {
                bookshelf = getBookshelfFromCursor(cursor);
            }
            cursor.close();
            ArrayList<Book> books = new ArrayList<>();
            books = bookshelf.getBooks();
            if (books != null) {
                if (!books.contains(book)) {
                    books.add(book);
                    bookshelf.setBooks(books);
                    updateBookshelf(bookshelf);
                }
            } else {
                books = new ArrayList<>();
                books.add(book);
                bookshelf.setBooks(books);
                updateBookshelf(bookshelf);
            }
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
        String rawString = cursor.getString(index);
        if (rawString != null) {
            String[] listOfIds = rawString.split(",");
            ArrayList<Book> books = new ArrayList<>();
            for (String id : listOfIds) {
                Book book = null;
                book = BooksDbDao.readBook(id);
                if (book != null) {
                    books.add(book);
                }
            }
            bookshelf.setBooks(books);
        }
        return bookshelf;
    }

}
