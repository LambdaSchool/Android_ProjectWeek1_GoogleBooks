package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BookshelvesFragment extends Fragment {
    private FloatingActionButton addBookshelfButton;
    private static ArrayList<Bookshelf> bookShelves;
    private RecyclerView recyclerView;
    private static BookshelvesListAdapter adapter;

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
        bookShelves = new ArrayList<>();
        addBookshelfButton = view.findViewById(R.id.add_bookshelf_button);
        recyclerView = view.findViewById(R.id.bookshelves_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapter = new BookshelvesListAdapter(getActivity(), bookShelves);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new getShelvesFromDb().execute();
        addBookshelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = null;
                if (getFragmentManager() != null) {
                    ft = getFragmentManager().beginTransaction();
                }
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

    public static class getShelvesFromDb extends AsyncTask<Void, Integer, ArrayList<Bookshelf>> {

        @Override
        protected ArrayList<Bookshelf> doInBackground(Void... voids) {
            return BooksViewModel.readBookshelves();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Bookshelf> shelves) {
            super.onPostExecute(shelves);
            bookShelves.clear();
            bookShelves.addAll(shelves);
            adapter.notifyDataSetChanged();
        }

    }

}
