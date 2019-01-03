package com.thadocizn.googlebooks.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thadocizn.googlebooks.BookDialog;
import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.adapters.BookAdapter;
import com.thadocizn.googlebooks.bookInfo.BookClass;
import com.thadocizn.googlebooks.bookInfo.BookViewModel;
import com.thadocizn.googlebooks.sqlObjects.SqlDbDao;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    private BookViewModel model;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        context = this;
        SqlDbDao.initializeInstance(context);


        recyclerView = findViewById(R.id.book_rv);
        linearLayoutManager = new LinearLayoutManager(this);
        model = ViewModelProviders.of(this).get(BookViewModel.class);
        Observer<ArrayList<BookClass>>listObserver = new Observer<ArrayList<BookClass>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BookClass> bookClasses) {
                adapter = new BookAdapter(bookClasses);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        };

        model.getBookList().observe(this, listObserver);

    }

  /*  @Override
    public void onItemClicked(View v) {
        BookDialog updateBook = new BookDialog();
        updateBook.show(getSupportFragmentManager(), "Book");
    }*/
}
