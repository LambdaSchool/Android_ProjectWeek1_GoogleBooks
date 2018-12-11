package com.joshuahalvorson.android_projectweek1_googlebooks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GoogleBooksApiDao {
    private static final String SEARCH_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String BOOKS_ARRAY_IN_API = "items";

    public static ArrayList<BookVolume> getBooksFromSearch(final String search){
        final ArrayList<BookVolume> books = new ArrayList<>();
        final String results = NetworkAdapter.httpRequest(SEARCH_URL + search + "&startIndex=0&maxResults=20", NetworkAdapter.GET);
        try {
            JSONObject topLevel = new JSONObject(results);
            JSONArray booksArray = topLevel.getJSONArray(BOOKS_ARRAY_IN_API);
            for(int i = 0; i < booksArray.length(); i++){
                JSONObject bookObject = booksArray.getJSONObject(i);
                JSONObject volumeInfo = bookObject.getJSONObject("volumeInfo");
                JSONObject imageUrls = volumeInfo.getJSONObject("imageLinks");
                String authors = "";
                if(volumeInfo.has("authors")){
                    JSONArray authorsArray = volumeInfo.getJSONArray("authors");
                    if(authorsArray != null){
                        for(int j = 0; j < authorsArray.length(); j++){
                            authors += authorsArray.get(j) + ", ";
                        }
                    }
                }
                String title = volumeInfo.getString("title");
                String imageUrl = imageUrls.getString("thumbnail");
                String publishedDate = volumeInfo.getString("publishedDate");
                int pages = 0;
                if(volumeInfo.has("pageCount")){
                    pages = volumeInfo.getInt("pageCount");
                }
                BookVolume book = new BookVolume(title, imageUrl, null, authors, publishedDate, pages, 0);
                books.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }
}
