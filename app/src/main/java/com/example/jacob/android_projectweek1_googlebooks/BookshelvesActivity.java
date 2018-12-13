package com.example.jacob.android_projectweek1_googlebooks;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;

public class BookshelvesActivity extends AppCompatActivity {

    Context context;
    private ArrayList<Bookshelf> bookshelves = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private BookshelvesListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelves);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                showMyDialog(context);
            }
        });

        bookshelves = BookshelfDbDao.readAllBookshelves();

        recyclerView = findViewById(R.id.recycler_view_bookshelves);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new BookshelvesListAdapter(bookshelves, this);
        recyclerView.setAdapter(listAdapter);
    }


    private void showMyDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_bookshelf);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        final EditText editText = dialog.findViewById(R.id.edit_text_dialog);
        Button saveButton = dialog.findViewById(R.id.button_dialog_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookshelfName = editText.getText().toString();
                bookshelfName = bookshelfName.replace(',',' ');
                bookshelfName = bookshelfName.replace("'","");
                BookshelfDbDao.addBookshelf(bookshelfName);
                listAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
