package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class AddBookshelfDialogFragment extends Fragment {
    private Button addBookshelfButton;
    private EditText bookshelfName;

    public AddBookshelfDialogFragment(){

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
        return inflater.inflate(R.layout.add_bookshelf_dialog_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addBookshelfButton = view.findViewById(R.id.add_bookshelf_button);
        bookshelfName = view.findViewById(R.id.bookshelf_name_text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addBookshelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add bookshelf to db with name of bookshelfName.getText().toString();
                Bookshelf bookshelf = new Bookshelf(bookshelfName.getText().toString());
                BooksViewModel.addBookshelf(bookshelf);
                Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (currentFragment instanceof BookshelvesFragment) {
                    FragmentTransaction ft = (getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.detach(currentFragment);
                    ft.attach(currentFragment);
                    ft.commit();
                    getFragmentManager().popBackStack();
                }
                Log.i("addBookshelfOnClick", "bookshelf added to db");
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
