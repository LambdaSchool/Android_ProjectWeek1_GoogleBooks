package com.thadocizn.googlebooks.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.thadocizn.googlebooks.BookshelfDialog;
import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.adapters.BookshelfAdapter;
import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;
import com.thadocizn.googlebooks.bookshelfInfo.BookshelfViewModel;
import com.thadocizn.googlebooks.sqlObjects.SqlDbDao;

import java.util.ArrayList;

public class BookshelfActivity extends AppCompatActivity {

    RecyclerView        recyclerView;
    BookshelfAdapter    adapter;
    Context             context;
    LinearLayoutManager linearLayoutManager;
    BookshelfViewModel  viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        context = this;
        SqlDbDao.initializeInstance(context);

        recyclerView        = findViewById(R.id.rvBookshelf);
        linearLayoutManager = new LinearLayoutManager(this);
        viewModel           = ViewModelProviders.of(this).get(BookshelfViewModel.class);
        Observer<ArrayList<Bookshelf>> observer = new Observer<ArrayList<Bookshelf>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Bookshelf> bookshelves) {

                    adapter = new BookshelfAdapter(bookshelves);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
            }
        };

        viewModel.getBookshelfList().observe(this, observer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BookshelfDialog().show(getSupportFragmentManager(), "bookshelf");
            }
        });

    }

}
