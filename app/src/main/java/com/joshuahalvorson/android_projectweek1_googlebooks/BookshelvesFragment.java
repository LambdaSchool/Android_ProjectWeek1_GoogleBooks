package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BookshelvesFragment extends Fragment {
    private FloatingActionButton addBookshelfButton;
    private ArrayList<Bookshelf> bookShelves;
    private LinearLayout scrollView;

    public BookshelvesFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bookshelves_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addBookshelfButton = view.findViewById(R.id.add_bookshelf_button);
        scrollView = view.findViewById(R.id.scroll_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookShelves = BooksViewModel.readBookshelves();
        for(int i = 0; i < bookShelves.size(); i++){
            final TextView tv = new TextView(getContext());
            final Bookshelf bookshelf = bookShelves.get(i);
            tv.setText(bookshelf.getName());
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(25);
            scrollView.addView(tv);
        }
        addBookshelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                AddBookshelfDialogFragment  addBookshelfDialogFragment = new AddBookshelfDialogFragment();
                ft.replace(R.id.dialog_container, addBookshelfDialogFragment, "add_bookshelf_fragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
