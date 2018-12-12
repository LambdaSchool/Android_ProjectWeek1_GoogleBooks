package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksInBookshelfDialogFragment extends Fragment {
    ArrayList<String> bookTitles;
    private Bookshelf bookshelf;
    private LinearLayout scrollView;

    public BooksInBookshelfDialogFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.books_in_bookshelf_dialog_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollView = view.findViewById(R.id.scroll_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookshelf = getArguments().getParcelable("bookshelf");
        bookTitles = BooksViewModel.readBooksInBookshelf(bookshelf);
        scrollView.removeAllViews();
        for(int i = 0; i < bookTitles.size(); i++){
            TextView tv = new TextView(getContext());
            tv.setText(bookTitles.get(i));
            tv.setTextSize(20);
            tv.setTextColor(Color.BLACK);
            scrollView.addView(tv);
        }
    }
}
