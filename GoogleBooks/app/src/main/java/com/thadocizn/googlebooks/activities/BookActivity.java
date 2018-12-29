package com.thadocizn.googlebooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.adapters.BookAdapter;
import com.thadocizn.googlebooks.bookInfo.BookClass;
import com.thadocizn.googlebooks.bookInfo.BookViewModel;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<BookClass> bookList;
    BookViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        model = new BookViewModel();
        recyclerView = findViewById(R.id.book_rv);
        linearLayoutManager = new LinearLayoutManager(this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                bookList = model.getBooks();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new BookAdapter(bookList);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();



    }
}
