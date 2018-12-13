package com.example.jacob.android_projectweek1_googlebooks;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class BookshelfViewActivity extends AppCompatActivity {

    Context context;
    private ArrayList<Book> books = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private BookshelvesListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf_view);

        context = this;
        int bookshelfId = getIntent().getIntExtra(Constants.VIEW_BOOKSHELF_KEY, -1);
        if (bookshelfId!=-1) {
            Bookshelf bookshelf = BookshelfDbDao.readBookshelf(bookshelfId);
            books.addAll(bookshelf.getBooks());
        }


        recyclerView = findViewById(R.id.recycler_view_bookshelves);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new BookshelfListAdapter(books, this);
        recyclerView.setAdapter(listAdapter);

    }
}
