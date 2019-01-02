package com.example.jacob.android_projectweek1_googlebooks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Context context;
    EditText editTextSearch;
    ProgressBar progressBar;
    private ArrayList<Book> booksList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    SearchListAdapter listAdapter;
    ArrayList<String> bookshelfTitles;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    Intent intent = new Intent(context, BookshelvesActivity.class);
                    startActivity(intent);
                    return true;
/*                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        context = this;

        BooksDbDao.initializeInstance(context);

        editTextSearch = findViewById(R.id.edit_text_search);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getBooksTask().execute(editTextSearch.getText().toString());
            }
        });

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


/*        FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_BOOKS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Book> books = new ArrayList<>();
                books = (ArrayList<Book>) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        bookshelfTitles = new ArrayList<>();
        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new SearchListAdapter(booksList, this, bookshelfTitles);
        recyclerView.setAdapter(listAdapter);
        updateSpinnerList();
    }

    public class getBooksTask extends AsyncTask<String, Integer, ArrayList<Book>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            booksList.clear();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);
            progressBar.setVisibility(View.GONE);
            booksList.addAll(books);
            listAdapter.notifyDataSetChanged();
            for (int i = 0; i < books.size(); ++i) {
                new getBookImageTask().execute(books.get(i).getImageUrl(), String.valueOf(i));
            }

        }

        @Override
        protected ArrayList<Book> doInBackground(String... strings) {
            return BookGoogleApiDao.searchBooks(strings[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }

    public class getBookImageTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected void onPostExecute(Integer index) {
            super.onPostExecute(index);
            listAdapter.notifyItemChanged(index);
        }


        @Override
        protected Integer doInBackground(String... strings) {
            BookGoogleApiDao.getImageFile(strings[0], context);
            return Integer.parseInt(strings[1]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        updateSpinnerList();
    }

    private void updateSpinnerList() {
        BookshelvesViewModel viewModel;

        viewModel = ViewModelProviders.of(this).get(BookshelvesViewModel.class);
        final Observer<ArrayList<Bookshelf>> observer = new Observer<ArrayList<Bookshelf>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Bookshelf> bookshelvesList) {
                if (bookshelvesList != null) {
                    bookshelfTitles.clear();
                    bookshelfTitles.add("");
                    for (int i =2; i < bookshelvesList.size(); i++) {
                        if (i >= Constants.DEFAULT_BOOKSHELVES.length) {
                            bookshelfTitles.add(bookshelvesList.get(i).getTitle());
                        }
                    }
                    listAdapter.notifyDataSetChanged();
                }
            }
        };
        viewModel.getBookshelvesList().observe(this, observer);
    }
}
