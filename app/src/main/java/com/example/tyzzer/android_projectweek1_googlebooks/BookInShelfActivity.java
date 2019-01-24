package com.example.tyzzer.android_projectweek1_googlebooks;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class BookInShelfActivity extends AppCompatActivity {

    Context context;
    private Activity activity;

    private RecyclerView listView;
    private LinearLayoutManager layoutManager;
    private BookInShelfAdapter listAdapter;
    private ArrayList<Bookshelf> bookshelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_in_shelf);

        context = this;
        activity = this;

        listView = findViewById(R.id.bookinshelf_recyclerview);
        listView.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(context);
        listView.setLayoutManager(layoutManager);

        final String bookshelfTitle = getIntent().getStringExtra(BookshelfAdapter.BOOKSHELF_KEY);
        }

    }
