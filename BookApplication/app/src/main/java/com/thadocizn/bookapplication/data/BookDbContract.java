package com.thadocizn.bookapplication.data;

import android.provider.BaseColumns;

public class BookDbContract {
    public static class BookEntry implements BaseColumns{

        public static final String TABLE_NAME = "books";

        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_IMAGE_URL = "image+";
    }
}
