package com.thadocizn.googlebooks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
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
    String strCategory;
    Integer read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        updateTitle                = findViewById(R.id.tvUpdateTitle);
        updateReview               = findViewById(R.id.etUpdateReview);
        readBook                   = findViewById(R.id.swithUpdateRead);
        readBook.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    read = 1;
                    readBook.setTextOn("yes");
                }else {
                    read = 0;
                    readBook.setTextOff("no");
                }
            }
        });

        model                      = new BookshelfViewModel();
        shelfList                  = new ArrayList<>(model.getBookshelfs());
        category                   = findViewById(R.id.spinnerUpdateCategory);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UpdateBookActivity.this.strCategory = new String();
                UpdateBookActivity.this.strCategory = shelfList.get(position).toString();
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

        updateTitle.setText(book.getBookTitle());
        updateReview.setText(book.getBookReview());

    }
}
