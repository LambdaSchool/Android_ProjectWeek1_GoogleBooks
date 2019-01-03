package com.example.jacob.android_projectweek1_googlebooks;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PublicFunctions {
    public static void bookshelfSelectionDialog(Context context, Book book, boolean addToDb) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_bookshelf);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        LinearLayout parentLayout = dialog.findViewById(R.id.layout_bookshelf_selection);


        ArrayList<Bookshelf> bookshelves = BookshelfDbDao.readAllBookshelves();
        if (bookshelves.size() > 2) {
            for (int i = 2; i < bookshelves.size(); ++i) {
                Bookshelf bookshelf = bookshelves.get(i);
                TextView view = new TextView(context);
                view.setText(bookshelf.getTitle());
                view.setTextSize(28);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (addToDb) {
                            BooksDbDao.addBook(book);
                        }
                        BookshelfDbDao.addBooktoBookshelf(view.getText().toString(), book);
                        dialog.dismiss();
                    }
                });
                parentLayout.addView(view);
            }
        } else {

        }
        dialog.show();
    }
}
