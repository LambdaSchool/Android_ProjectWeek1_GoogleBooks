package com.example.tyzzer.android_projectweek1_googlebooks;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookApiDao {
    public static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes/?q=";

    public static ArrayList<Book> searchBooks(String searchString) {
        ArrayList<Book> books = new ArrayList<>();
        String url = BASE_URL + searchString + "&maxResults=40&startIndex=0";
        final String result = NetworkAdapter.httpRequest(url, NetworkAdapter.GET);

        try {
            JSONObject topLevel = new JSONObject(result);
            JSONArray bookArray = topLevel.getJSONArray("items");
            for(int i = 0; i < bookArray.length(); i++){
                JSONObject bookObject = bookArray.getJSONObject(i).getJSONObject("volumeInfo");

                String title = bookObject.getString("title");

                String authors = "";
                if(bookObject.has("authors")){
                    JSONArray authorsArray = bookObject.getJSONArray("authors");
                    if(authorsArray != null){
                        for(int j = 0; j < authorsArray.length(); j++){
                            authors += authorsArray.get(j) + ", ";
                        }
                    }
                }

                JSONObject imageUrls = bookObject.getJSONObject("imageLinks");
                String imageUrl = imageUrls.getString("thumbnail");

                Book book = new Book(title, authors, imageUrl,null, 0);
                books.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;
    }
}
