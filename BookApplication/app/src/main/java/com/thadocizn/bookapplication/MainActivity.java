package com.thadocizn.bookapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.thadocizn.bookapplication.classes.Tag;
import com.thadocizn.bookapplication.data.BookDbDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BookDbDao.initializeInstance(this);

        Tag tag1 = new Tag("Shopping");
        Tag tag2 = new Tag("Important");
        Tag tag3 = new Tag("Watchlist");

        int tagId = BookDbDao.createTag(tag1);
        Log.d("Tag Count", "Tag Count: " + BookDbDao.getAllTags().size());
    }
}
