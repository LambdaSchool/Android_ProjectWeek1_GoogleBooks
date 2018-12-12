package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BooksDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Lambdabooks.db";

    public BooksDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BooksDbContract.BookEntry.SQL_CREATE_BOOKS_TABLE);
        db.execSQL(BooksDbContract.BookEntry.SQL_CREATE_BOOKSHELVES_TABLE);
        db.execSQL(BooksDbContract.BookEntry.SQL_CREATE_BOOKSHELVES_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BooksDbContract.BookEntry.SQL_DELETE_BOOKS_TABLE);
        db.execSQL(BooksDbContract.BookEntry.SQL_DELETE_BOOKSHELVES_TABLE);
        db.execSQL(BooksDbContract.BookEntry.SQL_DELETE_BOOKSHELVES_BOOKS_TABLE);
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db, oldVersion, newVersion);
    }
}
