package com.example.jacob.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BookViewActivity extends AppCompatActivity {

    EditText editTextReview;
    TextView textTitle, textAuthor, textPublication;
    Switch switchHasBeenRead;
    ImageView imageView;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        context = this;

        textTitle = findViewById(R.id.text_book_view_title);
        textAuthor = findViewById(R.id.text_book_view_author);
        textPublication = findViewById(R.id.text_book_view_publication);
        editTextReview = findViewById(R.id.edit_text_review);
        imageView = findViewById(R.id.image_book_view);
        switchHasBeenRead = findViewById(R.id.switch_has_been_read);

        String bookId = getIntent().getStringExtra(Constants.BOOK_EDIT_KEY);

        Book book = null;
        if (bookId != null) {
            book = BooksDbDao.readBook(bookId);
        }
        if (book != null) {
            textTitle.setText(book.getTitle());
            textAuthor.setText(book.getAuthor());
            textPublication.setText(book.getPublishedDate());
            editTextReview.setText(book.getReview());


            String imageUrl = book.getImageUrl();
            if (imageUrl != null) {
                String[] urlParts = imageUrl.substring(imageUrl.indexOf("id=") + 3).split("&");
                String fileName = urlParts[0];
                File file = getFileFromCache(fileName);
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    imageView.setImageResource(R.color.colorPrimaryDark);
                } catch (NullPointerException e) {
                   imageView.setImageResource(R.color.colorPrimaryDark);
                }
            } else {
               imageView.setImageResource(R.color.colorPrimaryDark);
            }
        }
    }

    private File getFileFromCache(String bookshelfText) {
        File file = null;
        File[] items = context.getCacheDir().listFiles();
        for (File item : items) {
            if (item.getName().contains(bookshelfText)) {
                file = item;
                break;
            }
        }
        return file;
        //TODO download image if it has lost its cache.  Somehow should spin that whole thing off on its own somewhere so not repeating code.
    }
}
