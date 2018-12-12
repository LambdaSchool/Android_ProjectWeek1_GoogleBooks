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

import java.util.ArrayList;

public class UsersBooksFragment extends Fragment {
    ArrayList<BookVolume> bookVolumes;
    private RecyclerView recyclerView;
    private UsersBooksListAdapter adapter;

    public UsersBooksFragment(){

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
        return inflater.inflate(R.layout.users_books_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookVolumes = new ArrayList<>();
        recyclerView = view.findViewById(R.id.search_results_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UsersBooksListAdapter(getActivity(), bookVolumes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new getBooksFromDb().execute();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public class getBooksFromDb extends AsyncTask<Void, Integer, ArrayList<BookVolume>> {

        @Override
        protected ArrayList<BookVolume> doInBackground(Void... voids) {
            return BooksViewModel.readBooks();
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
