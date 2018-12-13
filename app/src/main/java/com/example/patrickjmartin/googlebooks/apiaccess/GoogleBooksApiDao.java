package com.example.patrickjmartin.googlebooks.apiaccess;

import com.example.patrickjmartin.googlebooks.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GoogleBooksApiDao {

    //Basic search, no fancy paramteters,
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String MAX_RESULTS = "&startIndex=0&maxResults=25";

    public static ArrayList<Book> searchGoogleBooks(final String searchTerm) {
        final ArrayList<Book> foundBooks = new ArrayList<>();

        try {
            String results = NetworkAdapter.httpRequest(BASE_URL + searchTerm + MAX_RESULTS, NetworkAdapter.GET);
            JSONObject toplevel = new JSONObject(results);
            JSONArray dataJsonArray = toplevel.getJSONArray("items");
            for(int i = 0; i < dataJsonArray.length(); i++) {

                Book bookJSON = new Book(dataJsonArray.getJSONObject(i));
                foundBooks.add(bookJSON);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return foundBooks;
    }

    private static String parseAuthors(JSONArray authorsAry) {

        StringBuilder authorsString = new StringBuilder();

        for (int i = 0; i < authorsAry.length(); i++) {
            try {
                if (i == 0) {
                    authorsString.append(authorsAry.getString(i));
                } else {
                    authorsString.append(" - ").append(authorsAry.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return authorsString.toString();
    }

}
