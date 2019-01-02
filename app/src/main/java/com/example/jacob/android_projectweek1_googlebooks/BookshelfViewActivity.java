package com.example.jacob.android_projectweek1_googlebooks;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class BookshelfViewActivity extends AppCompatActivity {

    Context context;
    //    private ArrayList<Book> books = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private BookshelfListAdapter listAdapter;
    private BookshelfViewModel viewModel;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf_view);

        context = this;
        activity = this;

        recyclerView = findViewById(R.id.recycler_view_bookshelf);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        final int bookshelfId = getIntent().getIntExtra(Constants.VIEW_BOOKSHELF_KEY, -1);
        if (bookshelfId != -1) {
            viewModel = ViewModelProviders.of(this).get(BookshelfViewModel.class);
            final Observer<ArrayList<Book>> observer = new Observer<ArrayList<Book>>() {
                @Override
                public void onChanged(@Nullable ArrayList<Book> bookList) {
                    if (bookList != null) {
                        listAdapter = new BookshelfListAdapter(bookList, bookshelfId, activity, viewModel);
                        recyclerView.setAdapter(listAdapter);
                        for (int i = 0; i < bookList.size(); ++i){
                            new getBookImageTask().execute(bookList.get(i).getImageUrl(),String.valueOf(i));
                        }
                    }
                }
            };
            viewModel.getBooksList(bookshelfId).observe(this, observer);
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

}
