package com.example.tyzzer.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookApiDao {
    public static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes/?q=";

    public static ArrayList<Book> searchBooks(String searchString) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            String url = BASE_URL + searchString + "&maxResults=40&startIndex=0";
            final String result = NetworkAdapter.httpRequest(url, NetworkAdapter.GET);

            JSONObject dataObject = new JSONObject(result);
            JSONArray bookArray = dataObject.getJSONArray("items");

            for (int i = 0; i < bookArray.length(); i++) {
                Book book = new Book(bookArray.getJSONObject(i));
                books.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;
    }

    public static Bitmap getImage(String url){
        return NetworkAdapter.getBitmapFromURL(url);
    }
}
