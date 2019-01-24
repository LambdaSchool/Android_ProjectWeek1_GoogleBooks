package com.example.tyzzer.android_projectweek1_googlebooks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "BookDatabase.db";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BookDbContract.BookEntry.SQL_CREATE_BOOK_TABLE);
        sqLiteDatabase.execSQL(BookDbContract.BookEntry.SQL_CREATE_SHELF_TABLE);
        sqLiteDatabase.execSQL(BookDbContract.BookEntry.SQL_CREATE_BOOKINSHELF_TABLE);
        sqLiteDatabase.execSQL(BookDbContract.BookEntry.CREATE_SHELF_TO_READ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL(BookDbContract.BookEntry.SQL_DELETE_BOOK_TABLE);
        sqLiteDatabase.execSQL(BookDbContract.BookEntry.SQL_DELETE_SHELF_TABLE);
        sqLiteDatabase.execSQL(BookDbContract.BookEntry.SQL_DELETE_BOOKINSHELF_TABLE);
        this.onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db, oldVersion, newVersion);
    }
}