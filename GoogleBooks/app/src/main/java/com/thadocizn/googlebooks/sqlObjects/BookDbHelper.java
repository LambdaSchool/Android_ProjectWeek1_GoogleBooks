package com.thadocizn.googlebooks.sqlObjects;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDbHelper extends SQLiteOpenHelper {

    private static final String TAG = BookDbHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "book_manager";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookDbContract.BookEntry.SQL_CREATE_BOOK_TABLE);
        db.execSQL(BookDbContract.BookEntry.SQL_CREATE_BOOKSHELF_TABLE);
        db.execSQL(BookDbContract.BookEntry.SQL_CREATE_BOOK_BOOKSHELF_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BookDbContract.BookEntry.SQL_DELETE_TABLE_BOOK);
        db.execSQL(BookDbContract.BookEntry.SQL_DELETE_TABLE_BOOKSHELF);
        db.execSQL(BookDbContract.BookEntry.SQL_DELETE_TABLE_BOOK_BOOKSHELF);
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db, oldVersion, newVersion);
    }
}
