package com.thadocizn.bookapplication;

import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.thadocizn.bookapplication.classes.Book;
import com.thadocizn.bookapplication.classes.BookAdapter;
import com.thadocizn.bookapplication.data.BookDbDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    public static final int BOOK_LOADER_ID = 1;
    private EditText search;
    private RecyclerView recyclerView;
    private ImageButton searchButton;
    private View loadingIndicator;
    private BookAdapter adapter;
    private ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BookDbDao.initializeInstance(this);

        search =findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.recycleViewer);
        searchButton = findViewById(R.id.imageButton);
        loadingIndicator = findViewById(R.id.progressBar);
        bookList = new ArrayList<>();

        adapter = new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        if (checkConnection()){
            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        }else {
            loadingIndicator.setVisibility(View.GONE);
        }
    }

    private Boolean checkConnection(){
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        Boolean connected = false;
        if (connMgr != null){

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()){
                connected = true;
            }else {
                connected = false;
            }
        }
        return connected;
    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> data) {

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {

    }
}
