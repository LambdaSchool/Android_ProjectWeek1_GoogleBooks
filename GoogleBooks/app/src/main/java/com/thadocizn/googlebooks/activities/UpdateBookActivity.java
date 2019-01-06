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
import com.thadocizn.googlebooks.bookInfo.BookViewModel;
import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;
import com.thadocizn.googlebooks.bookshelfInfo.BookshelfViewModel;

import java.util.ArrayList;


public class UpdateBookActivity extends AppCompatActivity {

    TextView             updateTitle;
    EditText             updateReview;
    Spinner              category;
    Switch               readBook;
    ArrayList<Bookshelf> shelfList;
    BookshelfViewModel   model;
    String               strCategory;
    BookViewModel        bookViewModel;
    Integer              read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        model                      = new BookshelfViewModel();
        shelfList                  = new ArrayList<>();
        shelfList = model.getBookshelfs();

        updateTitle                = findViewById(R.id.tvUpdateTitle);
        updateReview               = findViewById(R.id.etUpdateReview);
        Intent intent     = getIntent();
        final BookClass book    = intent.getParcelableExtra(BookAdapter.CURRENT_BOOK);

        ArrayAdapter<Bookshelf> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                shelfList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category                   = findViewById(R.id.spinnerUpdateCategory);
        category.setAdapter(arrayAdapter);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UpdateBookActivity.this.strCategory = new String();
                UpdateBookActivity.this.strCategory = String.valueOf(shelfList.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        updateTitle.setText(book.getBookTitle());
        updateReview.setText(book.getBookReview());
        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = updateTitle.getText().toString();
                String review = updateReview.getText().toString();
                int readBook = read;
                BookClass bookClass = new BookClass(book.getBookKeyId(),
                        book.getBookId(),
                        title,
                        book.getBookImageUrl(),
                        review,
                        strCategory,
                        readBook);
                bookViewModel = new BookViewModel();
                bookViewModel.updateBook(bookClass);

            }
        });

        readBook                   = findViewById(R.id.swithUpdateRead);
        if (book.isReadBook() == 1){
            readBook.setChecked(true);
        }else {
            readBook.setChecked(false);
        }
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

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookViewModel = new BookViewModel();
                bookViewModel.deleteBook(book);

            }

        });

     }
}
