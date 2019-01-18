package com.example.tyzzer.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BookshelfActivity extends AppCompatActivity {

    private LinearLayout book_list;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookshelf_activity);
        book_list = findViewById(R.id.books_list);
        context = this;

        final ArrayList<Book> book = BookDbDao.listBooks();

        for(int i = 0; i < book.size(); i++) {
            final TextView bookList = new TextView(context);
            final Book getBooks = book.get(i);
            bookList.setText(getBooks.getTitle());
            bookList.setTextSize(18);
            bookList.setTypeface(null, Typeface.BOLD);
            bookList.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    bookList.setText("");
                    return false;
                }
            });
            book_list.addView(bookList);

        }
    }
}
