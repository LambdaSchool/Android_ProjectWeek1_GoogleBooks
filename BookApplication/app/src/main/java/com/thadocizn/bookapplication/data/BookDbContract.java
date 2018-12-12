package com.thadocizn.bookapplication.data;

import android.provider.BaseColumns;

public class BookDbContract {
    public static class BookEntry implements BaseColumns{


        public static final String TABLE_NAME_BOOK = "books";

        public static final String COLUMN_BOOK_ID                     = "id";
        public static final String COLUMN_NAME_BOOK_TITLE             = "title";
        public static final String COLUMN_NAME_BOOK_IMAGE_URL         = "image";
        public static final String COLUMN_NAME_BOOK_REVIEW            = "review";
        public static final String COLUMN_NAME_READ_BOOK              = "read_book";

        public static final String  SQL_CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_NAME_BOOK +
                " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOK_ID + " INTEGER, " +
                COLUMN_NAME_BOOK_TITLE + " TEXT, " +
                COLUMN_NAME_BOOK_IMAGE_URL + " TEXT, " +
                COLUMN_NAME_BOOK_REVIEW + " TEXT, " +
                COLUMN_NAME_READ_BOOK + " INTEGER);";

        public static final String TABLE_NAME_BOOKSHELF  = "bookshelf";

        public static final String COLUMN_NAME_BOOKSHELF_ID = BaseColumns._ID;
        public static final String COLUMN_NAME_BOOKSHELF_CATEGORY = "shelf_name";
        public static final String COLUMN_NAME_BOOK_KEY_ID = "id";

        public static final String SQL_CREATE_TABLE_BOOKSHELF = "CREATE TABLE " + TABLE_NAME_BOOKSHELF +
                " ( " +
                COLUMN_NAME_BOOKSHELF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_BOOKSHELF_CATEGORY + "TEXT, " +
                COLUMN_NAME_BOOK_KEY_ID + " INTEGER);";


        public static final String SQL_DELETE_TABLE_BOOK = "DROP TABLE IF EXISTS " + TABLE_NAME_BOOK + ";";
        public static final String SQL_DELETE_TABLE_BOOKSHELF = "DROP TABLE IF EXISTS " + TABLE_NAME_BOOKSHELF + ";";
    }
}
