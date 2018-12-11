package com.thadocizn.bookapplication.data;

import com.thadocizn.bookapplication.classes.Book;
import com.thadocizn.bookapplication.classes.NetworkAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookDao {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes/";
    private static final String QUERY = "?q=";
    private static final String SEARCH_URL = BASE_URL + QUERY;

    public static ArrayList<Book> findBooks(String bookSearch) {

        Book book = null;
        ArrayList<Book> books = new ArrayList<>();
        String url = SEARCH_URL + bookSearch;
        final String result = NetworkAdapter.httpRequest(url, NetworkAdapter.GET);

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
