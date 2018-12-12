package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.patrickjmartin.googlebooks.apiaccess.GoogleBooksApiDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private RecyclerView searchRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private BookSearchAdapter searchAdapter;
    private Button searchButton;

    private ArrayList<Book> test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity = this;

        searchRecyclerView = findViewById(R.id.recyclerView_search);
        searchRecyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this, 2);

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayList<Book> searchedBookList = GoogleBooksApiDao.searchGoogleBooks("mean");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                searchRecyclerView.setLayoutManager(gridLayoutManager);
                                searchAdapter = new BookSearchAdapter(searchedBookList, activity);

                                searchRecyclerView.setAdapter(searchAdapter);

                            }
                        });

                    }
                }).start();
            }
        });




    }
}
