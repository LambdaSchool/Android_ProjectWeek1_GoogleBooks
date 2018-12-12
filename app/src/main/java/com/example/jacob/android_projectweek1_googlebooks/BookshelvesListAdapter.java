package com.example.jacob.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookshelvesListAdapter extends RecyclerView.Adapter<BookshelvesListAdapter.ViewHolder> {

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView bookshelfTitle, bookContent;
            ImageView imageView;
            ViewGroup parentView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_bookshelves_element);
                bookshelfTitle = itemView.findViewById(R.id.text_bookshelves_element_title);
                bookContent = itemView.findViewById(R.id.text_bookshelves_element_content);
                parentView = itemView.findViewById(R.id.bookshelves_element_parent_layout);
            }
        }

        private ArrayList<Bookshelf> dataList;
        private Context context;
        private Activity activity;

    BookshelvesListAdapter(ArrayList<Bookshelf> dataList, Activity activity) {
            this.dataList = dataList;
            this.activity = activity;
        }

        @NonNull
        @Override
        public BookshelvesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            context = viewGroup.getContext();
            View view = LayoutInflater.from(
                    viewGroup.getContext())
                    .inflate(
                            R.layout.bookshelves_element_layout,
                            viewGroup,
                            false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookshelvesListAdapter.ViewHolder viewHolder, int i) {
            final Bookshelf data = dataList.get(i);
            viewHolder.bookshelfTitle.setText(data.getTitle());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
