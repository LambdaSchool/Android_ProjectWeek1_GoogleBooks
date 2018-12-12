package com.thadocizn.bookapplication;

import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.thadocizn.bookapplication.classes.Book;
import com.thadocizn.bookapplication.classes.BookAdapter;
import com.thadocizn.bookapplication.data.BookDao;
import com.thadocizn.bookapplication.data.BookDbDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public EditText search;
    private RecyclerView recyclerView;
    private ImageButton searchButton;
    private View loadingIndicator;
    private BookAdapter adapter;
    private ArrayList<Book> bookList;
    private LinearLayoutManager linearLayoutManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        BookDbDao.initializeInstance(this);


        search =findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.recycleViewer);
        searchButton = findViewById(R.id.imageButton);
        loadingIndicator = findViewById(R.id.progressBar);
        bookList = new ArrayList<>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        bookList = BookDao.findBooks(search.getText().toString());
                    }
                }).start();
            }
        });

        adapter = new BookAdapter(bookList);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

    }

}
