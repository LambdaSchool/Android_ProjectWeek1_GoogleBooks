package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    private BookDetailsAdapter searchedBooksAdapter;
    private GridLayoutManager gridLayoutManager;
    private BookSearchAdapter spinnerAdapter;

    private Library library;
    private ArrayList<String> bookshelfName;
    private ArrayList<Book> booksFromSearch, spinnerBookshelfSelection;

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
        spinnerBookshelfSelection = library.getBookshelf("All Books");

        selectBooksTextView = findViewById(R.id.library_selectedbook_textview);
        selectedBooksListView = findViewById(R.id.selected_books_listview);
        libraryRecyclerView = findViewById(R.id.library_recyclerview);

        searchBookshelvesEditText = findViewById(R.id.add_bookshelf_edittext);
        addBookshelfButton = findViewById(R.id.add_bookshelf_button);
        deleteBookshelfButton = findViewById(R.id.delete_bookshelf_button);
        spinnerBookshelves = findViewById(R.id.bookshelf_listing_spinner);

        bookDetailsButton = findViewById(R.id.library_bookdetails_button);
        searchBooksButton = findViewById(R.id.library_searchbooks_button);



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context,
                R.layout.support_simple_spinner_dropdown_item, bookshelfName);
        spinnerBookshelves.setAdapter(dataAdapter);

        spinnerBookshelves.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String shelfName =  (String) parent.getItemAtPosition(position);

                spinnerBookshelfSelection = library.getBookshelf(shelfName);


                if(shelfName.equals(" ")) {
                    addBookshelfButton.setEnabled(true);

                } else {
                    addBookshelfButton.setEnabled(false);
                    searchBookshelvesEditText.setText(shelfName);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (!booksFromSearch.isEmpty()) {

            searchedBooksAdapter = new BookDetailsAdapter(context, activity, booksFromSearch);
            selectedBooksListView.setAdapter(searchedBooksAdapter);

            selectBooksTextView.setText("Selected Books");

            selectedBooksListView.setOnItemLongClickListener((parent, view, position, id) -> {
                final Book addBook = booksFromSearch.get(position);
                library.addToBookshelf("All Books", addBook);
                booksFromSearch.remove(position);
                searchedBooksAdapter.notifyDataSetChanged();
                Toast.makeText(context, addBook.getTitle() + "added to 'All Books'", Toast.LENGTH_SHORT).show();

                if(booksFromSearch.size() == 0) {
                    selectBooksTextView.setText("");
                }

                return true;
            });
        }

        
        //Add Recycler View


        //addBookshelfButton.setEnabled(false);
        addBookshelfButton.setOnClickListener(v -> {
            //TODO
            //pull from edit
        });

        deleteBookshelfButton.setOnClickListener(v -> {
            //TODO

        });

        //Add Book Details Button

        //Add Search Books Button


    }
}
