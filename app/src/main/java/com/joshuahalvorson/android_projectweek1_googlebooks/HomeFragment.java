package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
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
import android.widget.EditText;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private EditText searchText;
    private RecyclerView recyclerView;
    private Button searchButton;
    private BookSearchListAdapter adapter;
    ArrayList<BookVolume> bookVolumes;

    public HomeFragment(){

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
        return inflater.inflate(R.layout.home_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchText = view.findViewById(R.id.search_for_book_edit_text);
        bookVolumes = new ArrayList<>();
        searchButton = view.findViewById(R.id.search_button);
        recyclerView = view.findViewById(R.id.search_results_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookSearchListAdapter(getActivity(), bookVolumes);
        recyclerView.setAdapter(adapter);
        BooksDbDao.initializeInstance(getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getSearchResults().execute(searchText.getText().toString());
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

    public class getSearchResults extends AsyncTask<String, Integer, ArrayList<BookVolume>>{

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

        @Override
        protected ArrayList<BookVolume> doInBackground(String... strings) {
            return GoogleBooksApiDao.getBooksFromSearch(strings[0]);
        }
    }
}
