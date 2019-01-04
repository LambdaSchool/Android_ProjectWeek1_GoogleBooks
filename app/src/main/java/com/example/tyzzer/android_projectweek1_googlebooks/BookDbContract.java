package com.example.tyzzer.android_projectweek1_googlebooks;

import android.provider.BaseColumns;

public class BookDbContract {
    public static class BookEntry implements BaseColumns {
        public static final String BOOK_TABLE_NAME =  "books";

        public final static String COLUMN_TITLE =     "title";
        public final static String COLUMN_AUTHOR =    "author";
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_REVIEW =    "review";
        public static final String COLUMN_BOOKSHELF = "bookshelf";
        public final static String COLUMN_READ =      "read";

        public final static String SQL_CREATE_BOOK_TABLE = "CREATE TABLE " +
                BOOK_TABLE_NAME +  "(" +
                _ID +              " INTEGER PRIMARY KEY, " +
                COLUMN_TITLE +     " TEXT, " +
                COLUMN_AUTHOR +    " TEXT, " +
                COLUMN_IMAGE_URL + " TEXT," +
                COLUMN_REVIEW +    " TEXT," +
                COLUMN_BOOKSHELF + " TEXT," +
                COLUMN_READ +      " INTEGER);";

        public static final String SQL_DELETE_BOOK_TABLE = "DROP TABLE IF EXISTS " + BOOK_TABLE_NAME + ";";


        public static final String SHELF_TABLE_NAME = "bookshelf";

        public static final String COLUMN_SHELF_NAME = "shelf_name";
        public static final String COLUMN_BOOK_ID =    "book_id";

        public static final String SQL_CREATE_SHELF_TABLE = "CREATE TABLE " +
                SHELF_TABLE_NAME +  "(" +
                _ID +               " INTEGER PRIMARY KEY, " +
                COLUMN_SHELF_NAME + " TEXT," +
                COLUMN_BOOK_ID +    " INTEGER);";



        public static final String SQL_DELETE_SHELF_TABLE = "DROP TABLE IF EXISTS " + SHELF_TABLE_NAME + ";";

    }
}