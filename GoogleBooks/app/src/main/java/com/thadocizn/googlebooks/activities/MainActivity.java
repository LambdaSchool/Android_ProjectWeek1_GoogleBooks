package com.thadocizn.googlebooks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.adapters.GoogleBookAdapter;
import com.thadocizn.googlebooks.bookInfo.BookClass;
import com.thadocizn.googlebooks.bookInfo.BookDaoClass;
import com.thadocizn.googlebooks.bookInfo.BookViewModel;
import com.thadocizn.googlebooks.sqlObjects.SqlDbDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText search;
    private RecyclerView recyclerView;
    private ImageButton searchButton;
    private GoogleBookAdapter adapter;
    private ArrayList<BookClass> bookList;
    private LinearLayoutManager linearLayoutManager;
    Context context;
    private BookViewModel model;
    CardView cardView;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.viewAllBooks:
                intent = new Intent(this, BookActivity.class);
                startActivity(intent);
                break;
            case R.id.viewBookshelves:
                intent = new Intent(this, BookshelfActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlDbDao.initializeInstance(this);

        context      = this;
        cardView     = findViewById(R.id.cardView);
        search       = findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.rvBook);
        searchButton = findViewById(R.id.imageButton);
        bookList     = new ArrayList<>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        bookList = BookDaoClass.findBooks(search.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new GoogleBookAdapter(bookList);
                                recyclerView.setHasFixedSize(true);
                                linearLayoutManager = new LinearLayoutManager(context);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    }

                }).start();
            }
        });
    }

}
