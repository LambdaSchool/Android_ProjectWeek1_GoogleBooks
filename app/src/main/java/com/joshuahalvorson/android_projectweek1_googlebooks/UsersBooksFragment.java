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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersBooksFragment extends Fragment {
    private LinearLayout scrollView;
    ArrayList<BookVolume> bookVolumes;

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
        scrollView = view.findViewById(R.id.scroll_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookVolumes = BookVolumeViewModel.readBooks();
        for(int i = 0; i < bookVolumes.size(); i++){
            final TextView tv = new TextView(getContext());
            final BookVolume bookVolume = bookVolumes.get(i);
            tv.setText(bookVolume.getTitle());
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(25);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bookVolume.isHasRead() == 0){
                        tv.setTextColor(Color.YELLOW);
                        bookVolume.setHasRead(1);
                        BookVolumeViewModel.updateBook(bookVolume);
                    }else{
                        tv.setTextColor(Color.BLACK);
                        bookVolume.setHasRead(0);
                        BookVolumeViewModel.updateBook(bookVolume);
                    }

                }
            });
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    BookVolumeViewModel.deleteBook(bookVolume);
                    tv.setVisibility(View.GONE);
                    return false;
                }
            });
            scrollView.addView(tv);
        }
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
