package com.example.jacob.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookshelvesListAdapter extends RecyclerView.Adapter<BookshelvesListAdapter.ViewHolder> {

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView bookshelfTitle;
            ViewGroup parentView;
            ImageButton buttonDelete;
            View view;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                bookshelfTitle = itemView.findViewById(R.id.text_bookshelves_element_title);
                parentView = itemView.findViewById(R.id.bookshelves_element_parent_layout);
                buttonDelete = itemView.findViewById(R.id.button_bookshelf_delete);
                view = itemView.findViewById(R.id.layout_bookshelves);

            }
        }

        private ArrayList<Bookshelf> dataList;
        private Context context;
        private Activity activity;
        private BookshelvesViewModel viewModel;

    BookshelvesListAdapter(ArrayList<Bookshelf> dataList, Activity activity,BookshelvesViewModel viewModel) {
            this.dataList = dataList;
            this.activity = activity;
            this.viewModel = viewModel;
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
        public void onBindViewHolder(@NonNull final BookshelvesListAdapter.ViewHolder viewHolder, int i) {
            final Bookshelf data = dataList.get(i);
            viewHolder.bookshelfTitle.setText(data.getTitle());
            viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.deleteBookshelf(data.getId());
//                    BookshelfDbDao.deleteBookshelf(data.getId());
                }
            });
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookshelfViewActivity.class);
                    intent.putExtra(Constants.VIEW_BOOKSHELF_KEY, data.getId());
                    activity.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
