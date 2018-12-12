package com.example.patrickjmartin.googlebooks.apiaccess;

import com.example.patrickjmartin.googlebooks.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GoogleBooksApiDao {

    //Basic search, no fancy paramteters,
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    public static ArrayList<Book> searchGoogleBooks(final String searchTerm) {
        ArrayList<Book> foundBooks = new ArrayList<>();

        //Runnable dealing with a ton of elements in items
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String results = NetworkAdapter.httpRequest(BASE_URL + searchTerm, NetworkAdapter.GET);
                    JSONObject toplevel = new JSONObject(results);
                    JSONArray dataJsonArray = toplevel.getJSONArray("items");
                    for(int i = 0; i < dataJsonArray.length(); i++) {
                    JSONObject volumeInfo = dataJsonArray.getJSONObject(i).getJSONObject("volumeInfo");
                    String title = volumeInfo.getString("title");
                    JSONArray authorsJsonArray = volumeInfo.getJSONArray("authors");
                    String author = (authorsJsonArray == null) ? "" :


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        return foundBooks;
    }

    private String parseAuthors(JSONArray authorsAry) {

        Strring authors;

        return authorsString;
    }

}
