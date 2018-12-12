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

    public static final int BOOK_LOADER_ID = 1;
    private EditText search;
    private RecyclerView recyclerView;
    private ImageButton searchButton;
    private View loadingIndicator;
    private BookAdapter adapter;
    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BookDbDao.initializeInstance(this);

        search =findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.recycleViewer);
        searchButton = findViewById(R.id.imageButton);
        loadingIndicator = findViewById(R.id.progressBar);
        bookList = new ArrayList<>();

        adapter = new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    public class getBooks extends AsyncTask<String, Integer, ArrayList<Book>>{

        @Override
        protected ArrayList<Book> doInBackground(String... strings) {
            return BookDao.findBooks(strings[0]);
        }
    }
}
