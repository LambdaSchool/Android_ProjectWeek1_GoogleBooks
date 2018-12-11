package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private EditText searchText;
    private LinearLayout resultsContainer;
    private Button searchButton;
    ArrayList<BookVolume> booksList;
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
        resultsContainer = view.findViewById(R.id.search_results_container);
        searchButton = view.findViewById(R.id.search_button);
        BooksDbDao.initializeInstance(getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        booksList = GoogleBooksApiDao.getBooksFromSearch(searchText.getText().toString());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resultsContainer.removeAllViews();
                                for(int i = 0; i < booksList.size(); i++){
                                    final TextView tv = new TextView(getContext());
                                    final String title = booksList.get(i).getTitle();
                                    final BookVolume bookVolume = booksList.get(i);
                                    tv.setText(booksList.get(i).getTitle());
                                    tv.setTextSize(20);
                                    tv.setTextColor(Color.BLACK);
                                    tv.setOnLongClickListener(new View.OnLongClickListener() {
                                        @Override
                                        public boolean onLongClick(View v) {
                                            BookVolumeViewModel.addBook(bookVolume);
                                            tv.setTextColor(Color.YELLOW);
                                            return false;
                                        }
                                    });
                                    resultsContainer.addView(tv);
                                }
                            }
                        });
                    }
                }).start();
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
