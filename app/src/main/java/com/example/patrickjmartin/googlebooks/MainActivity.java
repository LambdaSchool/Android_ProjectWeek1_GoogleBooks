package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.patrickjmartin.googlebooks.apiaccess.GoogleBooksApiDao;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private RecyclerView searchRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private BookSearchAdapter searchAdapter;
    private EditText searchBox;

    private ArrayList<Book> results;
    private ArrayList<Book> selectedBooks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        context = this;
        activity = this;
        results = new ArrayList<>();


        searchBox = findViewById(R.id.editText);


        searchRecyclerView = findViewById(R.id.recyclerView_search);

        searchRecyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(context, 2);

        searchRecyclerView.setLayoutManager(gridLayoutManager);
        searchAdapter = new BookSearchAdapter(results, activity);

        findViewById(R.id.search_books_button).setOnClickListener(v -> new getBooksTask().
                execute(searchBox.getText().toString()));

        searchRecyclerView.setAdapter(searchAdapter);

        findViewById(R.id.view_books_details).setOnClickListener(v -> {
            Intent bookDetailsIntent = new Intent(context, BookDetailsActivity.class);
            selectedBooks = searchAdapter.getChosenBooks();

            if(selectedBooks != null) {
                selectedBooks.forEach((n) -> n.setSelected(false));
                bookDetailsIntent.putParcelableArrayListExtra("foundbooks", selectedBooks);
                startActivity(bookDetailsIntent);
            } else startActivity(bookDetailsIntent);
        });

        findViewById(R.id.view_bookshelf_button).setOnClickListener(v -> {
            Intent bookShelfIntent = new Intent(context, BookshelfActivity.class);
            selectedBooks = searchAdapter.getChosenBooks();

            if (selectedBooks != null) {
                selectedBooks.forEach((n) -> n.setSelected(false));
                bookShelfIntent.putParcelableArrayListExtra("organizeBooks", selectedBooks);
                startActivity(bookShelfIntent);
            } else startActivity(bookShelfIntent);
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        if (selectedBooks != null ) {
            selectedBooks.clear();
        }

    }

    public class getBooksTask extends AsyncTask<String, Void, ArrayList<Book>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected ArrayList<Book> doInBackground(String... strings) {
            return GoogleBooksApiDao.searchGoogleBooks(strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);
            results.clear();
            results.addAll(books);
            searchAdapter.notifyDataSetChanged();


        }
    }
}


//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        final ArrayList<Book> searchedBookList =
//                                GoogleBooksApiDao.searchGoogleBooks(searchBox.getText().toString());
//
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                searchRecyclerView.removeAllViews();
//                                searchRecyclerView.setLayoutManager(gridLayoutManager);
//                                searchAdapter = new BookSearchAdapter(searchedBookList, activity);
//                                searchRecyclerView.setAdapter(searchAdapter);
//
//                            }
//                        });
//
//                    }
//                }).start();