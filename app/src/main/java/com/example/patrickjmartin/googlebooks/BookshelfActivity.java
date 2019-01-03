package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class BookshelfActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;

    private RecyclerView libraryRecyclerView;
    private ListView selectedBooksListView;

    private EditText searchBookshelvesEditText;
    private Button addBookshelfButton, deleteBookshelfButton, bookDetailsButton, searchBooksButton;
    private Spinner spinnerBookshelves;
    private TextView selectBooksTextView;

    private Library library;
    private ArrayList<String> bookshelfName;
    private ArrayList<Book> booksFromSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        context = this;
        activity = this;

        Intent intent = getIntent();
        library = Library.getINSTANCE();
        bookshelfName = library.getBookshelfNames();
        bookshelfName.add(0, " ");
        booksFromSearch = intent.getParcelableArrayListExtra("organizeBooks");

        selectedBooksListView = findViewById(R.id.selected_books_listview);
        libraryRecyclerView = findViewById(R.id.library_recyclerview);

        searchBookshelvesEditText = findViewById(R.id.add_bookshelf_edittext);
        addBookshelfButton = findViewById(R.id.add_bookshelf_button);
        deleteBookshelfButton = findViewById(R.id.delete_bookshelf_button);
        spinnerBookshelves = findViewById(R.id.bookshelf_listing_spinner);

        bookDetailsButton = findViewById(R.id.library_bookdetails_button);
        searchBooksButton = findViewById(R.id.library_searchbooks_button);



        addBookshelfButton.setOnClickListener(v -> {
            //TODO
            //pull from edit
        });

        deleteBookshelfButton.setOnClickListener(v -> {
            //TODO

        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                R.layout.support_simple_spinner_dropdown_item, bookshelfName);
        spinnerBookshelves.setAdapter(dataAdapter);

        spinnerBookshelves.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);

                //TODO
                //Get String value, look up bookshelf, turn into array
                //apply to recycler view.
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if(booksFromSearch != null) {

            //Create Listview

        }

        //Create Recycler View

        //Button to go back to Search

        //Button to go back to details


    }
}
