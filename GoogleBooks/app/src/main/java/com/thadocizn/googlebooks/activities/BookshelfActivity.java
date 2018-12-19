package com.thadocizn.googlebooks.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.adapters.BookshelfAdapter;
import com.thadocizn.googlebooks.bookInfo.BookRepository;
import com.thadocizn.googlebooks.bookInfo.BookViewModel;
import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;
import com.thadocizn.googlebooks.sqlObjects.SqlDbDao;

import java.util.ArrayList;

public class BookshelfActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Bookshelf> shelves;
    BookshelfAdapter adapter;
    Context context;
    private LinearLayoutManager linearLayoutManager;
    BookViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);
        viewModel = new BookViewModel();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });


       /* new Thread(new Runnable() {
            @Override
            public void run() {
                shelves = SqlDbDao.getBookshelves();
                adapter = new BookshelfAdapter(shelves);
                recyclerView.setHasFixedSize(true);
                linearLayoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        }).start();*/
    }

}
