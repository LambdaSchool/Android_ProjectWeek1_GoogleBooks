package com.example.patrickjmartin.googlebooks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BookDbDao {

    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {
        BookDbHelper helper = helper = new BookDbHelper(context);
        db = helper.getWritableDatabase();
    }

    //Create CRUD functions
}
