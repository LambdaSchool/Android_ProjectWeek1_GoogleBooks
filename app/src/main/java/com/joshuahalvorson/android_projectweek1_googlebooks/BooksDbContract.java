package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.provider.BaseColumns;

public class BooksDbContract {
    public static class BookEntry implements BaseColumns{
        public static final String BOOKS_TABLE_NAME = "books";
        public static final String BOOKS_COLUMN_TITLE = "title";
        public static final String BOOKS_COLUMN_IMAGE_URL = "image";
        public static final String BOOKS_COLUMN_USER_REVIEW = "user_review";
        public static final String BOOKS_COLUMN_AUTHORS = "authors";
        public static final String BOOKS_COLUMN_PUBLISHED_DATE = "published_date";
        public static final String BOOKS_COLUMN_PAGES = "pages";
        public static final String BOOKS_COLUMN_HAS_READ = "has_read";

        public static final String SQL_CREATE_BOOKS_TABLE = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s TEXT PRIMARY KEY, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s INTEGER, " +
                        "%s INTEGER);",
                BOOKS_TABLE_NAME,
                _ID,
                BOOKS_COLUMN_TITLE,
                BOOKS_COLUMN_IMAGE_URL,
                BOOKS_COLUMN_USER_REVIEW,
                BOOKS_COLUMN_AUTHORS,
                BOOKS_COLUMN_PUBLISHED_DATE,
                BOOKS_COLUMN_PAGES,
                BOOKS_COLUMN_HAS_READ);

        public static final String SQL_DELETE_BOOKS_TABLE = "DROP TABLE IF EXISTS "
                + BOOKS_TABLE_NAME + ";";

        public static final String BOOKSHELVES_TABLE_NAME = "bookshelves";
        public static final String BOOKSHELVES_COLUMN_TITLE = "bookshelf_name";

        public static final String SQL_CREATE_BOOKSHELVES_TABLE = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s TEXT PRIMARY KEY, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s INTEGER, " +
                        "%s INTEGER);",
                BOOKSHELVES_TABLE_NAME,
                _ID,
                BOOKSHELVES_COLUMN_TITLE);

        public static final String SQL_DELETE_BOOKSHELVES_TABLE = "DROP TABLE IF EXISTS "
                + BOOKSHELVES_TABLE_NAME + ";";

    }
}
