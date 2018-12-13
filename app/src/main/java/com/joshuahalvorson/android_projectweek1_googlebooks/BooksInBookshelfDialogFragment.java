package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksInBookshelfDialogFragment extends Fragment {
    ArrayList<BookVolume> bookVolumes;
    private Bookshelf bookshelf;
    private LinearLayout scrollView;
    private RecyclerView recyclerView;
    private BooksInBookshelfAdapter adapter;

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
        recyclerView = view.findViewById(R.id.books_in_shelf_recycler_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookshelf = getArguments().getParcelable("bookshelf");
        bookVolumes = BooksViewModel.readBooksInBookshelf(bookshelf);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BooksInBookshelfAdapter(getActivity(), bookVolumes, bookshelf);
        recyclerView.setAdapter(adapter);
        new getBooksInShelf().execute();
        /*scrollView.removeAllViews();
        for(int i = 0; i < bookVolumes.size(); i++){
            TextView tv = new TextView(getContext());
            final BookVolume bookVolume = bookVolumes.get(i);
            tv.setText(bookVolume.getTitle());
            tv.setTextSize(20);
            tv.setTextColor(Color.BLACK);
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    BooksViewModel.removeBookshelfBookRelation(bookshelf, bookVolume);
                    getFragmentManager().popBackStack();
                    return false;
                }
            });
            scrollView.addView(tv);
        }*/
    }

    public class getBooksInShelf extends AsyncTask<Void, Integer, ArrayList<BookVolume>> {

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
