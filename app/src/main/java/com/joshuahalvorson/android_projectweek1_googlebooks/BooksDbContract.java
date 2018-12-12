package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.provider.BaseColumns;

public class BooksDbContract {
    public static class BookEntry implements BaseColumns {
        public static final String BOOKS_TABLE_NAME = "books";
        public static final String BOOKS_COLUMN_TITLE = "title";
        public static final String BOOKS_COLUMN_IMAGE_URL = "image";
        public static final String BOOKS_COLUMN_USER_REVIEW = "user_review";
        public static final String BOOKS_COLUMN_AUTHORS = "authors";
        public static final String BOOKS_COLUMN_PUBLISHED_DATE = "published_date";
        public static final String BOOKS_COLUMN_PAGES = "pages";
        public static final String BOOKS_COLUMN_HAS_READ = "has_read";
        public static final String BOOKS_COLUMN_IS_FAVORITE = "is_favorite";

        public static final String SQL_CREATE_BOOKS_TABLE = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s INTEGER, " +
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
                BOOKS_COLUMN_HAS_READ,
                BOOKS_COLUMN_IS_FAVORITE);

        public static final String SQL_DELETE_BOOKS_TABLE = "DROP TABLE IF EXISTS "
                + BOOKS_TABLE_NAME + ";";

        /////////////////////////////////////////////////////////////////////////////

        public static final String BOOKSHELVES_TABLE_NAME = "bookshelves";
        public static final String BOOKSHELVES_COLUMN_TITLE = "bookshelf_name";

        public static final String SQL_CREATE_BOOKSHELVES_TABLE = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT);",
                BOOKSHELVES_TABLE_NAME,
                _ID,
                BOOKSHELVES_COLUMN_TITLE);

        public static final String SQL_DELETE_BOOKSHELVES_TABLE = "DROP TABLE IF EXISTS "
                + BOOKSHELVES_TABLE_NAME + ";";

        /////////////////////////////////////////////////////////////////////////////

        public static final String BOOKSHELVES_BOOKS_TABLE_NAME = "bookshelves_books";
        public static final String BOOKSHELVES_BOOKS_TABLE_COLUMN_BOOKSHELF_ID = "bookshelf_id";
        public static final String BOOKSHELVES_BOOKS_TABLE_COLUMN_BOOK_ID = "book_id";

        public static final String SQL_CREATE_BOOKSHELVES_BOOKS_TABLE = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s), " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s));",
                BOOKSHELVES_BOOKS_TABLE_NAME,
                _ID,
                BOOKSHELVES_BOOKS_TABLE_COLUMN_BOOKSHELF_ID,
                BOOKSHELVES_BOOKS_TABLE_COLUMN_BOOK_ID,
                BOOKSHELVES_BOOKS_TABLE_COLUMN_BOOKSHELF_ID, BOOKSHELVES_TABLE_NAME, BOOKSHELVES_COLUMN_TITLE,
                BOOKSHELVES_BOOKS_TABLE_COLUMN_BOOK_ID, BOOKS_TABLE_NAME, BOOKS_COLUMN_TITLE);

        public static final String SQL_DELETE_BOOKSHELVES_BOOKS_TABLE = "DROP TABLE IF EXISTS "
                + BOOKSHELVES_TABLE_NAME + ";";

        /////////////////////////////////////////////////////////////////////////////

        public static final String CREATE_DEFAULT_SHELF_FAVORITES =
                String.format("INSERT INTO %s (%s) VALUES ('%s');",
                        BOOKSHELVES_TABLE_NAME, BOOKSHELVES_COLUMN_TITLE, "Favorites");

        public static final String CREATE_DEFAULT_SHELF_HAS_READ =
                String.format("INSERT INTO %s (%s) VALUES ('%s');",
                        BOOKSHELVES_TABLE_NAME, BOOKSHELVES_COLUMN_TITLE, "Books youve read");


    }
}
