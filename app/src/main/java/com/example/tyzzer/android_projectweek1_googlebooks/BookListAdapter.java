package com.example.tyzzer.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder>{
    Bitmap bitmap = null;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle, bookAuthor;
        ImageView bookCover;
        ViewGroup parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCover = itemView.findViewById(R.id.book_cover);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookAuthor = itemView.findViewById(R.id.book_author);
            parentView = itemView.findViewById(R.id.book_element_parent_layout);
        }
    }

    private ArrayList<Book> dataList;
    private Context context;
    private Activity activity;

    BookListAdapter(ArrayList<Book> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(
                viewGroup.getContext())
                .inflate(
                        R.layout.book_element_layout,
                        viewGroup,
                        false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.ViewHolder viewHolder, int i) {
        final Book data = dataList.get(i);
        viewHolder.bookTitle.setText(data.getTitle());
        viewHolder.bookAuthor.setText(data.getAuthor());
        new Thread(new Runnable() {
            @Override
            public void run() {
                bitmap = BookApiDao.getImage(data.getImageUrl());
            }
        }).start();
        viewHolder.bookCover.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
