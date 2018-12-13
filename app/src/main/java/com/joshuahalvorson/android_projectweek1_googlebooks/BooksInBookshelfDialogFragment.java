package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class BooksInBookshelfDialogFragment extends Fragment {
    static ArrayList<BookVolume> bookVolumes;
    private static Bookshelf bookshelf;
    private RecyclerView recyclerView;
    private static BooksInBookshelfAdapter adapter;
    private Button deleteShelfButton;

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
        recyclerView = view.findViewById(R.id.books_in_shelf_recycler_view);
        deleteShelfButton = view.findViewById(R.id.delete_bookshelf_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            bookshelf = getArguments().getParcelable("bookshelf");
        }
        bookVolumes = BooksViewModel.readBooksInBookshelf(bookshelf);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BooksInBookshelfAdapter(getActivity(), bookVolumes, bookshelf);
        recyclerView.setAdapter(adapter);
        deleteShelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bookshelf.getName().equals("Favorites") || bookshelf.getName().equals("Already read")){
                    Toast.makeText(getContext(),
                            "Cannot delete default bookshelf",
                            Toast.LENGTH_LONG).show();
                }else{
                    BooksViewModel.deleteBookshelf(bookshelf, bookVolumes);
                    new BookshelvesFragment.getShelvesFromDb().execute();
                    if (getFragmentManager() != null) {
                        getFragmentManager().popBackStack();
                    }
                }
            }
        });
        new getBooksInShelf().execute();
    }

    public static class getBooksInShelf extends AsyncTask<Void, Integer, ArrayList<BookVolume>> {

        @Override
        protected ArrayList<BookVolume> doInBackground(Void... voids) {
            return BooksViewModel.readBooksInBookshelf(bookshelf);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<BookVolume> volumes) {
            super.onPostExecute(volumes);
            bookVolumes.clear();
            bookVolumes.addAll(volumes);
            adapter.notifyDataSetChanged();
        }

    }
}
