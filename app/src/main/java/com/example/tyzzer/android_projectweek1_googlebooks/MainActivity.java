package com.example.tyzzer.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    private Activity activity;

    private EditText searchText;
    private Button searchButton;
    private Button saveBooks;

    private BookListAdapter listAdapter;
    private ArrayList<Book> books;

    private RecyclerView listView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_text);
        searchButton = findViewById(R.id.search_button);
        saveBooks = findViewById(R.id.save_books);
        context = this;

        books = new ArrayList<>();

        listView = findViewById(R.id.book_search_recyclerview);
        listView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        listView.setLayoutManager(layoutManager);

        listAdapter = new BookListAdapter(books, activity);
        listView.setAdapter(listAdapter);

        BookDbDao.initializeInstance(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new searchBooksTask().execute(searchText.getText().toString());
            }
        });

        saveBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookshelfActivity.class);
                startActivity(intent);
            }
        });

    }

    public class searchBooksTask extends AsyncTask<String, Integer, ArrayList<Book>> {
        @Override
        protected void onPostExecute(ArrayList<Book> bookList){
            super.onPostExecute(bookList);
            books.clear();
            books.addAll(bookList);
            listAdapter.notifyDataSetChanged();
        }

        @Override
        protected ArrayList<Book> doInBackground(String... strings) {
            return BookApiDao.searchBooks(strings[0]);
        }
    }
}
