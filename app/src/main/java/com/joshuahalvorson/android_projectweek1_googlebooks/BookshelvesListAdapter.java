package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BookshelvesListAdapter extends RecyclerView.Adapter<BookshelvesListAdapter.ViewHolder>{
    private ArrayList<Bookshelf> bookShelves;
    private Activity activity;

    public BookshelvesListAdapter(Activity activity, ArrayList<Bookshelf> bookShelves) {
        this.bookShelves = bookShelves;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BookshelvesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.bookshelf_list_element_layout, viewGroup, false);
        return new BookshelvesListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookshelvesListAdapter.ViewHolder viewHolder, int i) {
        final Bookshelf bookshelf = bookShelves.get(i);
        String name = bookshelf.getName();
        viewHolder.titleText.setText(name);
        viewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                BooksInBookshelfDialogFragment  booksInBookshelfDialogFragment= new BooksInBookshelfDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("bookshelf", bookshelf);
                booksInBookshelfDialogFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.dialog_container, booksInBookshelfDialogFragment, "books_in_bookshelf_fragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookShelves.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        CardView parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.bookshelf_name);
            parentView = itemView.findViewById(R.id.parent_view);
        }
    }
}
