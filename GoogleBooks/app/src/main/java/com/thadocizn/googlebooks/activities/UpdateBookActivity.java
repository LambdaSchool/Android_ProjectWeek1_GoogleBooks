package com.thadocizn.googlebooks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.adapters.BookAdapter;
import com.thadocizn.googlebooks.bookInfo.BookClass;
import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;
import com.thadocizn.googlebooks.bookshelfInfo.BookshelfViewModel;

import java.util.ArrayList;


public class UpdateBookActivity extends AppCompatActivity {

    TextView updateTitle;
    EditText updateReview;
    Spinner category;
    Switch readBook;
    ArrayList<Bookshelf> shelfList;
    BookshelfViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        updateTitle                = findViewById(R.id.tvUpdateTitle);
        updateReview               = findViewById(R.id.etUpdateReview);
        readBook                   = findViewById(R.id.swithUpdateRead);
        model                      = new BookshelfViewModel();
        shelfList                  = new ArrayList<>(model.getBookshelfs());
        category                   = findViewById(R.id.spinnerUpdateCategory);
        final String[] strCategory = new String[1];
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCategory[0] = shelfList.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent intent     = getIntent();
        BookClass book    = intent.getParcelableExtra(BookAdapter.CURRENT_BOOK);

        ArrayAdapter<Bookshelf> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                shelfList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(arrayAdapter);

        String bookTitle  = book.getBookTitle();
        String bookReview = book.getBookReview();

        updateTitle.setText(bookTitle);
        updateReview.setText(bookReview);

    }
}
