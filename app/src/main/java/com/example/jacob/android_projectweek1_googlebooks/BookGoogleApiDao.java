package com.example.jacob.android_projectweek1_googlebooks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookGoogleApiDao {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes/";
    private static final String SEARCH_SUFFIX = "?q=";

    private static final String SEARCH_URL = BASE_URL + SEARCH_SUFFIX;


    public static ArrayList<Book> searchBooks(String searchString) {
        Book book = null;
        ArrayList<Book> books = new ArrayList<>();
        String fullUrl = SEARCH_URL + searchString;
        final String result = NetworkAdapter.httpRequest(fullUrl, NetworkAdapter.GET);
        Log.i("GoogleAPI DAO", result);
        try {
            JSONObject topLevel = new JSONObject(result);
            JSONArray jsonArray = topLevel.getJSONArray("items");


            for (int i = 0; i < jsonArray.length(); ++i) {
                book = new Book(jsonArray.getJSONObject(i));
                books.add(book);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }


}
