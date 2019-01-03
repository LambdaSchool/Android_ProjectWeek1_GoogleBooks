package com.thadocizn.googlebooks.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.bookInfo.BookClass;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    ArrayList<BookClass> myBookList;
    Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        context   = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_book_activity, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        BookClass currentBook = myBookList.get(i);

        myViewHolder.bookId.setText(currentBook.getBookId());
        myViewHolder.bookTitle.setText(currentBook.getBookTitle());
        myViewHolder.bookReview.setText(currentBook.getBookReview());
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public BookAdapter(ArrayList<BookClass> myBookList) {
        this.myBookList = myBookList;
    }

    @Override
    public int getItemCount() {
        return myBookList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookId;
        TextView bookTitle;
        TextView bookReview;
        LinearLayout parentLayout;

        public MyViewHolder(@NonNull View book) {
            super(book);
            bookId = book.findViewById(R.id.book_bookId);
            bookTitle = book.findViewById(R.id.book_bookTitle);
            bookReview = book.findViewById(R.id.book_bookReview);
            parentLayout = book.findViewById(R.id.bookActivity);

        }
    }
}
