package com.example.tyzzer.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetails extends AppCompatActivity {

    private Context context;
    private Activity activity;

    private Button saveShelf, saveReview;
    private ImageView detailBookCover;
    private EditText bookReviewEdit, shelfNameEdit;
    private TextView bookReview, bookTitle;
    private CheckBox readCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        detailBookCover = findViewById(R.id.detail_book_cover);
        bookTitle = findViewById(R.id.detail_book_title);
//        saveShelf = findViewById(R.id.save_bookshelf_button);
        saveReview = findViewById(R.id.save_review_button);
        bookReview = findViewById(R.id.review_text);
        bookReviewEdit = findViewById(R.id.edit_review_text);
//        shelfNameEdit = findViewById(R.id.edit_bookshelf_text);
        readCheck = findViewById(R.id.read_check);

        saveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book reviewBook = new Book(null, null, null, bookReviewEdit.getText().toString(), 0);
                BookDbDao.updateBookReview(reviewBook);
            }
        });
//
//        new Thread(() -> {
//            final Bitmap bitmap = NetworkAdapter.getBitmapFromURL(book.getImage());
//            activity.runOnUiThread(() -> bookDetailsImage.setImageBitmap(bitmap));
//        }).start();

    }

}
