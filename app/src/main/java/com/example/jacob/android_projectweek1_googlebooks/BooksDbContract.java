package com.example.jacob.android_projectweek1_googlebooks;

import android.provider.BaseColumns;

public class BooksDbContract {
    static class BookEntry implements BaseColumns {
        static final String TABLE_NAME = "books";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_AUTHOR = "author";
        static final String COLUMN_NAME_PUBLISHED_DATE = "published_date";
        static final String COLUMN_NAME_IMAGE_URL = "image_url";
        static final String COLUMN_NAME_REVIEW = "review";
        static final String COLUMN_NAME_HAS_BEEN_READ = "has_been_read";



        static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" +
                _ID + " STRING, " +
                COLUMN_NAME_TITLE + " STRING, " +
                COLUMN_NAME_AUTHOR + " STRING, " +
                COLUMN_NAME_PUBLISHED_DATE + " STRING, " +
                COLUMN_NAME_IMAGE_URL + " STRING, " +
                COLUMN_NAME_REVIEW + " STRING, " +
                COLUMN_NAME_HAS_BEEN_READ + " INTEGER);";

        static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }
}
