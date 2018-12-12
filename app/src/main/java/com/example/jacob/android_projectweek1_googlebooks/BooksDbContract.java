package com.example.jacob.android_projectweek1_googlebooks;

import android.provider.BaseColumns;

public class BooksDbContract {
    static class BookEntry implements BaseColumns {
        static final String BOOK_TABLE_NAME = "books";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_AUTHOR = "author";
        static final String COLUMN_NAME_PUBLISHED_DATE = "published_date";
        static final String COLUMN_NAME_IMAGE_URL = "image_url";
        static final String COLUMN_NAME_REVIEW = "review";
        static final String COLUMN_NAME_HAS_BEEN_READ = "has_been_read";
        static final String COLUMN_NAME_BOOKSHELVES = "bookshelves";

        static final String BOOKSHELF_TABLE_NAME = "bookshelves";

        //TODO use a JOIN table.

        static final String SQL_BOOK_TABLE = "CREATE TABLE " + BOOK_TABLE_NAME +
                " (" +
                _ID + " STRING, " +
                COLUMN_NAME_TITLE + " STRING, " +
                COLUMN_NAME_AUTHOR + " STRING, " +
                COLUMN_NAME_PUBLISHED_DATE + " STRING, " +
                COLUMN_NAME_IMAGE_URL + " STRING, " +
                COLUMN_NAME_REVIEW + " STRING, " +
                COLUMN_NAME_HAS_BEEN_READ + " INTEGER, " +
                COLUMN_NAME_BOOKSHELVES +" STRING);";

        static final String SQL_BOOKSHELF_TABLE = "CREATE TABLE " + BOOKSHELF_TABLE_NAME +
                " (" +
                _ID + " STRING, " +
                COLUMN_NAME_TITLE +" STRING);";

        static final String SQL_CREATE_TABLE = SQL_BOOK_TABLE + " " + SQL_BOOKSHELF_TABLE;

        static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + BOOK_TABLE_NAME + ";";
    }
}
