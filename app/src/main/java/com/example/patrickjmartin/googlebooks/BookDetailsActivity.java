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
import android.widget.BaseAdapter;
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
        library = Library.getINSTANCE();

        bookDetailsImage = findViewById(R.id.book_details_image);

        bookDetailsTextView = findViewById(R.id.book_details_textview);
        bookReviewTextBox = findViewById(R.id.review_edittext);

        saveReviewButton = findViewById(R.id.save_review_button);
        searchBooksButton = findViewById(R.id.search_books_button);
        organizeLibraryButton = findViewById(R.id.organize_bookshelf_button);

        addLibrarySwitch = findViewById(R.id.add_to_library_switch);
        readSwitch = findViewById(R.id.read_switch);
        favoriteBookSwitch = findViewById(R.id.favorites_switch);

        bookIntentResult = intent.getParcelableArrayListExtra("foundbooks");
        booksDetailsListView = findViewById(R.id.book_details_listview);
        detailsAdapter = new BookDetailsAdapter(context,activity, bookIntentResult);
        booksDetailsListView.setAdapter(detailsAdapter);

        booksDetailsListView.setOnItemClickListener((parent, view, position, id) -> {
            final Book clickBook = bookIntentResult.get(position);
            addLibrarySwitch.setOnCheckedChangeListener(null);
            readSwitch.setOnCheckedChangeListener(null);
            addLibrarySwitch.setOnCheckedChangeListener(null);

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
                    library.addToBookshelf("Reviewed", clickBook);
                    library.addToBookshelf("Read", clickBook);
                    clickBook.setSelected(true);
                    clickBook.setRead(1);
                    addLibrarySwitch.setChecked(true);
                    readSwitch.setChecked(true);
                    detailsAdapter.notifyDataSetChanged();
                }
            });

            addLibrarySwitch.setChecked(clickBook.isSelected());
            if(clickBook.getRead() == 0) {
                readSwitch.setChecked(false);
            } else {
                readSwitch.setChecked(true);
            }
            favoriteBookSwitch.setChecked(clickBook.isFavorite());

            addLibrarySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {

                if (isChecked) {
                    clickBook.setSelected(true);
                    library.addToBookshelf("All Books", clickBook);
                } else {
                    clickBook.setSelected(false);
                    clickBook.setRead(0);
                    clickBook.setFavorite(false);
                    library.removeFromBookshelf("All Books", clickBook);
                }
                detailsAdapter.notifyDataSetChanged();
            });

            readSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    clickBook.setRead(1);
                    clickBook.setSelected(true);
                    library.addToBookshelf("All Books", clickBook);
                    library.addToBookshelf("Read", clickBook);
                } else {
                    clickBook.setRead(0);
                    library.removeFromBookshelf("Read", clickBook);
                }
            });

            favoriteBookSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    clickBook.setSelected(true);
                    clickBook.setRead(1);
                    clickBook.setFavorite(true);
                    library.addToBookshelf("All Books", clickBook);
                    library.addToBookshelf("Read", clickBook);
                    library.addToBookshelf("Favorites", clickBook);
                } else {
                    clickBook.setRead(0);
                    clickBook.setFavorite(false);
                    library.removeFromBookshelf("Read", clickBook);
                    library.removeFromBookshelf("Favorites", clickBook);
                }
            });
    });

        searchBooksButton.setOnClickListener(v -> {
            bookIntentResult.forEach((n) -> n.setSelected(false));
            Intent toSearch = new Intent(context, MainActivity.class);
            startActivity(toSearch);
        });

        organizeLibraryButton.setOnClickListener(v-> {
            bookIntentResult.forEach((n) -> n.setSelected(false));
            Intent toLibrary = new Intent(context, BookshelfActivity.class);
            startActivity(toLibrary);
        });



    }
}
