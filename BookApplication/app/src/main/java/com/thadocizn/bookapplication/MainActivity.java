package com.thadocizn.bookapplication;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.thadocizn.bookapplication.data.BookDbDao;

public class MainActivity extends AppCompatActivity {

    private EditText search;
    private RecyclerView recyclerView;
    private ImageButton searchButton;
    private View loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BookDbDao.initializeInstance(this);

        search =findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.recycleViewer);
        searchButton = findViewById(R.id.imageButton);
        loadingIndicator = findViewById(R.id.progressBar);


    }
}
