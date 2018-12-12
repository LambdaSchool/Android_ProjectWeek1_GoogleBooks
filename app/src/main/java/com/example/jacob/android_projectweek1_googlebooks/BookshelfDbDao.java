package com.example.jacob.android_projectweek1_googlebooks;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class BookshelfDbDao extends BooksDbDao {

    static void addBookshelf(String name) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(BooksDbContract.BookEntry.COLUMN_NAME_TITLE, name);
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
                Bookshelf bookshelf = new Bookshelf(-1,null, null);
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry._ID);
                bookshelf.setId(cursor.getInt(index));
                index = cursor.getColumnIndexOrThrow(BooksDbContract.BookEntry.COLUMN_NAME_TITLE);
                bookshelf.setTitle(cursor.getString(index));
                bookshelves.add(bookshelf);
            }
            cursor.close();
        }
        return bookshelves;
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


}
