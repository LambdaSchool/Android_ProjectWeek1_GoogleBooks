package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        bookVolumes = BooksViewModel.readBooks();
        for(int i = 0; i < bookVolumes.size(); i++){
            final TextView tv = new TextView(getContext());
            final BookVolume bookVolume = bookVolumes.get(i);
            tv.setText(bookVolume.getTitle());
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(25);
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    EditBookDialogFragment  editBookDialogFragment= new EditBookDialogFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("book", bookVolume);
                    editBookDialogFragment.setArguments(bundle);
                    ft.add(R.id.dialog_container, editBookDialogFragment, "edit_book_fragment");
                    ft.addToBackStack(null);
                    ft.commit();
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
