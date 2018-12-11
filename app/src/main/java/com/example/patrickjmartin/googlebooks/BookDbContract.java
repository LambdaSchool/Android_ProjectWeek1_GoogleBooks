package com.example.patrickjmartin.googlebooks;

import android.provider.BaseColumns;

public class BookDbContract {

    public static class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "books";

        public static final String COLUMN_NAME_BOOK_TITLE = "title";
        public static final String COLUMN_NAME_BOOK_AUTHOR = "author";
        public static final String COLUMN_NAME_BOOK_REVIEW = "review";
        public static final String COLUMN_NAME_BOOK_PUBLISH_DATE = "publishDate";
        public static final String COLUMN_NAME_BOOK_API_ID = "googleBooksID";
        public static final String COLUMN_NAME_BOOK_IMAGE = "image";
        public static final String COLUMN_NAME_READ = "read";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " ( " +
                _ID + "INTEGER PRIMARY KEY," +
                COLUMN_NAME_BOOK_TITLE  + " TEXT, " +
                COLUMN_NAME_BOOK_AUTHOR + " TEXT, " +
                COLUMN_NAME_BOOK_REVIEW + " TEXT, " +
                COLUMN_NAME_BOOK_PUBLISH_DATE + " TEXT, " +
                COLUMN_NAME_BOOK_API_ID + " TEXT, " +
                COLUMN_NAME_BOOK_IMAGE + " TEXT, " +
                COLUMN_NAME_READ + " INTEGER);";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    }
}


//    private String title, author, review, publishDate, googleBooksID, image;
//    private Boolean isRead;