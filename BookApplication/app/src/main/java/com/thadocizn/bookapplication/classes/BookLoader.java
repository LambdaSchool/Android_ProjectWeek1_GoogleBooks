package com.thadocizn.bookapplication.classes;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {
    private final String url;

    public BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public ArrayList<Book> loadInBackground() {
        if (url == null) {
            return null;
        }
        ArrayList<Book> books = new ArrayList<>();
        return books;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
