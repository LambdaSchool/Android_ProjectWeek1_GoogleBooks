package com.thadocizn.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.thadocizn.myapplication.R;

public class EditBookActivity extends AppCompatActivity {

    EditText title;
    EditText bookReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        title = findViewById(R.id.etBookTitle);
        bookReview = findViewById(R.id.etBookReview);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTitle = title.getText().toString();
                String strBookReview = bookReview.getText().toString();

            }
        });
    }
}
