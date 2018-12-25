package com.thadocizn.googlebooks.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.thadocizn.googlebooks.BookshelfDialog;
import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.adapters.BookAdapter;
import com.thadocizn.googlebooks.adapters.BookshelfAdapter;
import com.thadocizn.googlebooks.bookInfo.BookClass;
import com.thadocizn.googlebooks.bookInfo.BookViewModel;
import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;

import java.util.ArrayList;

import com.thadocizn.googlebooks.BookshelfDialog;
import com.thadocizn.googlebooks.bookshelfInfo.BookshelfViewModel;

public class BookshelfActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Bookshelf> shelves;
    BookshelfAdapter adapter;
    Context context;
    private LinearLayoutManager linearLayoutManager;
    BookshelfViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_bookshelf);
        Intent intent = getIntent();
        BookClass book = intent.getParcelableExtra(BookAdapter.CURRENT_BOOK);
        long bookKeyId =  book.getBookKeyId();

        recyclerView = findViewById(R.id.rvBookshelf);

        //todo recycler is not showing names of bookshelves fix bug
        viewModel = ViewModelProviders.of(this).get(BookshelfViewModel.class);
        Observer<ArrayList<Bookshelf>> observer = new Observer<ArrayList<Bookshelf>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Bookshelf> bookshelves) {
                if (bookshelves != null){
                    if (adapter == null){
                        adapter = new BookshelfAdapter(bookshelves);
                        getBookshelves();
                    }
                }
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

    private void getBookshelves() {
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}
