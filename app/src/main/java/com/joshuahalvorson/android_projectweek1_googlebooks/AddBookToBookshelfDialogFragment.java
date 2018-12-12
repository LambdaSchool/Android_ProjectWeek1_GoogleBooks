package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddBookToBookshelfDialogFragment extends Fragment {
    private ArrayList<Bookshelf> bookshelves;
    private BookVolume bookVolume;
    private Spinner selectShelf;
    private Button addBook;
    private Context mContext;

    public AddBookToBookshelfDialogFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_book_to_shelf_dialog_fragment_layout, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectShelf = view.findViewById(R.id.bookshelf_list);
        addBook = view.findViewById(R.id.add_book_to_shelf_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookshelves = BooksViewModel.readBookshelves();
        bookVolume = getArguments().getParcelable("book");
        ArrayList<String> bookshelvesNames = new ArrayList<>();
        for(Bookshelf b : bookshelves){
            bookshelvesNames.add(b.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, bookshelvesNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectShelf.setAdapter(dataAdapter);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add book to bookshelf
                String bookshelfName = selectShelf.getSelectedItem().toString();
                for(Bookshelf b : bookshelves){
                    if(b.getName().equals(bookshelfName)){
                        BooksViewModel.addBookshelfBookRelation(b, bookVolume);
                        Log.i("onClickAddBook", "book added to bookshelf " + bookshelfName);
                    }
                }
                getFragmentManager().popBackStack();
            }
        });
    }
}
