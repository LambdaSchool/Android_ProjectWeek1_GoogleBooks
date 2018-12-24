package com.thadocizn.googlebooks.sqlObjects;

import android.provider.BaseColumns;

public class BookDbContract {

    static class BookEntry implements BaseColumns {

        public static final String TABLE_NAME_BOOK                 = "book";

        public static final String BOOK_COLUMN_NAME_BOOK_KEY        = "_ID";
        public static final String BOOK_COLUMN_NAME_BOOK_ID        = "book_id";
        public static final String BOOK_COLUMN_NAME_BOOK_TITLE     = "title";
        public static final String BOOK_COLUMN_NAME_BOOK_IMAGE_URL = "image";
        public static final String BOOK_COLUMN_NAME_BOOK_REVIEW    = "review";
        public static final String BOOK_COLUMN_NAME_READ_BOOK      = "read_book";

        public static final String SQL_CREATE_BOOK_TABLE = String.format(
                "CREATE TABLE" + " %s" +
                        " ( " +
                        "%s LONG PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s INTEGER);",
                TABLE_NAME_BOOK,
                BOOK_COLUMN_NAME_BOOK_KEY,
                BOOK_COLUMN_NAME_BOOK_ID,
                BOOK_COLUMN_NAME_BOOK_TITLE,
                BOOK_COLUMN_NAME_BOOK_IMAGE_URL,
                BOOK_COLUMN_NAME_BOOK_REVIEW,
                BOOK_COLUMN_NAME_READ_BOOK);

        public static final String BOOKSHELF_TABLE_NAME             = "bookshelf";

        public static final String BOOKSHELF_COLUMN_NAME_SHELF_NAME = "bookshelf_name";
        public static final String BOOKSHELF_COLUMN_NAME_SHELF_ID = "_ID";

        public static final String SQL_CREATE_BOOKSHELF_TABLE       = String.format(
                "CREATE TABLE " + "%s" +
                        " ( " +
                        "%s LONG PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT);",

                BOOKSHELF_TABLE_NAME,
                BOOKSHELF_COLUMN_NAME_SHELF_ID,
                BOOKSHELF_COLUMN_NAME_SHELF_NAME);

        public static final String BOOKS_BOOKSHELF_TABLE = "book_bookshelf";
        public static final String BOOKS_BOOKSHELF_COLUMN_NAME_BOOK_ID = "book_id";
        public static final String BOOKS_BOOKSHELF_COLUMN_NAME_BOOkSHELF_ID = "bookshelf_id";

        public static final String SQL_CREATE_BOOK_BOOKSHELF_TABLE = String.format(
                "CREATE TABLE " + "%s" +
                        " ( " +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s), " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s));",
                BOOKS_BOOKSHELF_TABLE,
                _ID,
                BOOKS_BOOKSHELF_COLUMN_NAME_BOOK_ID,
                BOOKS_BOOKSHELF_COLUMN_NAME_BOOkSHELF_ID,
                BOOKS_BOOKSHELF_COLUMN_NAME_BOOK_ID,
                TABLE_NAME_BOOK, BOOK_COLUMN_NAME_BOOK_ID,
                BOOKS_BOOKSHELF_COLUMN_NAME_BOOkSHELF_ID,
                BOOKSHELF_TABLE_NAME, BOOKSHELF_COLUMN_NAME_SHELF_ID);

        public static final String SQL_DELETE_TABLE_BOOK =
                "DROP TABLE IF EXISTS " + TABLE_NAME_BOOK + ";";

        public static final String SQL_DELETE_TABLE_BOOKSHELF =
                "DROP TABLE IF EXISTS " + BOOKSHELF_TABLE_NAME + ";";

        public static final String SQL_DELETE_TABLE_BOOK_BOOKSHELF =
                "DROP TABLE IF EXISTS " + BOOKS_BOOKSHELF_TABLE + ";";
    }
}
