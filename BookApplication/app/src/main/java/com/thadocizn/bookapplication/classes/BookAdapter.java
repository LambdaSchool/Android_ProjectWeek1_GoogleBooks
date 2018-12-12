package com.thadocizn.bookapplication.classes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thadocizn.bookapplication.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    public BookAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    private ArrayList<Book> bookList;
    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder myViewHolder, int i) {
        Book currentBook = bookList.get(i);

        myViewHolder.bookTitle.setText(currentBook.getBookTitle());
        myViewHolder.bookReview.setText(currentBook.getBookReview());
        myViewHolder.readBook.setText(currentBook.isReadBook());
       // myViewHolder.bookImage.setImageBitmap(currentBook.getBookImageUrl());
    }

    @Override
    public int getItemCount() {
        if (bookList == null){
            return 0;

        }else {
            return bookList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;
        TextView bookReview;
        TextView readBook;
        ImageView bookImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle= itemView.findViewById(R.id.tvBook);
            bookReview= itemView.findViewById(R.id.tvReview);
            readBook= itemView.findViewById(R.id.tvReadBook);
            bookImage= itemView.findViewById(R.id.ivBookImage);
        }
    }
}
