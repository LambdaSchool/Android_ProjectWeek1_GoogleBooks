package com.example.tyzzer.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class BookshelfActivity extends AppCompatActivity {

    Context context;
    private Activity activity;

    private BookshelfAdapter listAdapter;
    private ArrayList<Bookshelf> bookshelf;

    private RecyclerView listView;
    private LinearLayoutManager layoutManager;

    private EditText shelfText;
    private Button addButton, deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookshelf_activity);
        final ArrayList<Bookshelf> bookshelf = BookDbDao.listBookshelves();

        listView = findViewById(R.id.bookshelf_list_recyclerview);
        listView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        listView.setLayoutManager(layoutManager);

        context = this;

        addButton = findViewById(R.id.add_shelf_button);
        deleteButton = findViewById(R.id.delete_shelf_button);
        shelfText = findViewById(R.id.bookshelf_name);

        listAdapter = new BookshelfAdapter(bookshelf,activity);
        listView.setAdapter(listAdapter);





        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bookshelf addShelf = new Bookshelf(shelfText.getText().toString());
                BookDbDao.addBookshelf(addShelf);
                listAdapter.notifyDataSetChanged();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bookshelf removeShelf = new Bookshelf(shelfText.getText().toString());
                BookDbDao.addBookshelf(removeShelf);
                listAdapter.notifyDataSetChanged();
            }
        });

    }
}
