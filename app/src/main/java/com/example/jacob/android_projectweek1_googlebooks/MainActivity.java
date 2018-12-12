package com.example.jacob.android_projectweek1_googlebooks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;

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
    private SearchListAdapter listAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        editTextSearch = findViewById(R.id.edit_text_search);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getBooksTask().execute(editTextSearch.getText().toString());
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new SearchListAdapter(booksList, this);
        recyclerView.setAdapter(listAdapter);
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
                String imageUrl = books.get(i).getImageUrl();
                String[] urlParts = imageUrl.substring(imageUrl.indexOf("id=") + 3).split("&");
                String searchText = urlParts[0];
                File[] items = context.getCacheDir().listFiles();
                Boolean fileFound = false;
                for (File item : items) {
                    if (item.getName().contains(searchText)) {
                        fileFound = true;
                        break;
                    }
                }
                if (!fileFound) {
                    new getBookImageTask().execute(imageUrl, String.valueOf(i));
                }
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

    public class getBookImageTask extends AsyncTask<String, Integer, Wrapper> {

        @Override
        protected void onPostExecute(Wrapper wrapper) {
            super.onPostExecute(wrapper);
            File imageFile = wrapper.getFile();
            //TODO get rid of wrapper.
            int index = wrapper.getIndex();
            listAdapter.notifyItemChanged(index);
        }

        @Override
        protected Wrapper doInBackground(String... strings) {
            File file = BookGoogleApiDao.getImageFile(strings[0], context);
            int index = Integer.parseInt(strings[1]);
            Wrapper wrapper = new Wrapper();
            wrapper.setFile(file);
            wrapper.setIndex(index);
            return wrapper;
        }
    }

    private class Wrapper {
        public File file;
        public int index;

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
