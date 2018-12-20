package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity {


    private Context context;
    private Activity activity;

    private RecyclerView booksDetails


    private ArrayList<Book> bookIntentResult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
    }
}
