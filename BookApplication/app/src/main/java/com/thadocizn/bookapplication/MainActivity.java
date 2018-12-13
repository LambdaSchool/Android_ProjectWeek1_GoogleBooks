package com.thadocizn.bookapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.thadocizn.bookapplication.classes.Book;
import com.thadocizn.bookapplication.classes.BookAdapter;
import com.thadocizn.bookapplication.data.BookDao;
import com.thadocizn.bookapplication.data.BookDbDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    public EditText search;
    private RecyclerView recyclerView;
    private ImageButton searchButton;
    private BookAdapter adapter;
    private ArrayList<Book> bookList;
    private LinearLayoutManager linearLayoutManager;
    Context context;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        BookDbDao.initializeInstance(this);


        cardView = findViewById(R.id.cardView);
        search = findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.recycleViewer);
        searchButton = findViewById(R.id.imageButton);
        bookList = new ArrayList<>();
        adapter = new BookAdapter(bookList);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        bookList = BookDao.findBooks(search.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new BookAdapter(bookList);
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
        adapter.setOnClickListener(this);
    }

    @Override
    public void onClick(int id, int position) {

        Intent intent = new Intent(this, EditBookActivity.class);
        startActivity(intent);

        //TODO find out if there is another way
       /* LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popup = inflater.inflate(R.layout.activity_edit_book, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popup, width, height, focusable);
        popupWindow.showAtLocation(cardView, Gravity.CENTER, 0, 0);*/
    }
}

