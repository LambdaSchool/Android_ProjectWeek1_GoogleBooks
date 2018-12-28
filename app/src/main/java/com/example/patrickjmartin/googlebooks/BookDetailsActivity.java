package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.patrickjmartin.googlebooks.apiaccess.NetworkAdapter;

import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity {


    private Context context;
    private Activity activity;



    private Button saveReviewButton, searchBooksButton, organizeLibraryButton;
    private ImageView bookDetailsImage;
    private TextView bookDetailsTextView;
    private EditText bookReviewTextBox;
    private Switch addLibrarySwitch, readSwitch, favoriteBookSwitch;
    private ListView booksDetailsListView;
    private BookDetailsAdapter detailsAdapter;


    private ArrayList<Book> bookIntentResult;

    private Library library;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        context = this;
        activity = this;



        Intent intent = getIntent();

        bookDetailsImage = findViewById(R.id.book_details_image);

        bookDetailsTextView = findViewById(R.id.book_details_textview);
        bookReviewTextBox = findViewById(R.id.review_edittext);

        saveReviewButton = findViewById(R.id.save_review_button);

        addLibrarySwitch = findViewById(R.id.add_to_library_switch);
        readSwitch = findViewById(R.id.read_switch);
        favoriteBookSwitch = findViewById(R.id.favorites_switch);

        bookIntentResult = intent.getParcelableArrayListExtra("foundbooks");
        booksDetailsListView = findViewById(R.id.book_details_listview);
        detailsAdapter = new BookDetailsAdapter(context,activity, bookIntentResult);
        booksDetailsListView.setAdapter(detailsAdapter);

        booksDetailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Book clickBook = (Book) parent.getItemAtPosition(position);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = NetworkAdapter.httpImageRequest(clickBook.getImage());
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bookDetailsImage.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();

                bookDetailsTextView.setText(clickBook.toString());

                saveReviewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickBook.setReview(bookReviewTextBox.getText().toString());
                        bookReviewTextBox.getText().clear();
                        bookDetailsTextView.setText(clickBook.toString());
                        //Add to Reviewed Bookshelf
                        //Add to Read (assumed since it was reviewed)
                    }
                });


                addLibrarySwitch.setChecked(clickBook.isSelected());
                if(clickBook.getRead() == 0) {
                    readSwitch.setChecked(false);
                } else {
                    readSwitch.setChecked(true);
                }
                favoriteBookSwitch.setChecked(clickBook.isFavorite());

                addLibrarySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //TODO
                    }
                });

                readSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //TODO
                    }
                });

                favoriteBookSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //TODO
                    }
                });






        }
        });


    }
}
