package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity {


    private Context context;
    private Activity activity;


    private RecyclerView booksDetails;
    private Button saveReviewButton, searchBooksButton, organizeLibraryButton;
    private EditText bookReviewTextBox;
    private ListView booksDetailsListView;
    private BookDetailsAdapter detailsAdapter;


    private ArrayList<Book> bookIntentResult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        context = this;
        activity = this;

        Intent intent = getIntent();

        bookIntentResult = intent.getParcelableArrayListExtra("foundbooks");

        booksDetailsListView = findViewById(R.id.book_details_listview);

        detailsAdapter = new BookDetailsAdapter(context,activity, bookIntentResult);
        booksDetailsListView.setAdapter(detailsAdapter);


    }
}
