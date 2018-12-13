package com.thadocizn.bookapplication.classes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.bookapplication.EditBookActivity;
import com.thadocizn.bookapplication.ItemClickListener;
import com.thadocizn.bookapplication.R;

import java.util.List;

import static java.security.AccessController.getContext;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<Book> bookList;
    private static ItemClickListener clickListener;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity, parent, false);
        return new MyViewHolder(view);
    }
    public void setOnClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder myViewHolder, final int i) {
        Book currentBook = bookList.get(i);

        myViewHolder.bookTitle.setText(currentBook.getBookTitle());
        myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(i, -1);
            }
        });

       // myViewHolder.bookReview.setText(currentBook.getBookReview());
        // TODO bug
//        myViewHolder.readBook.setText(currentBook.isReadBook());
        // myViewHolder.bookImage.setImageBitmap(currentBook.getBookImageUrl());
    }

    @Override
    public int getItemCount() {
        if (bookList == null) {
            return 0;

        } else {
            return bookList.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;
        TextView bookReview;
        TextView readBook;
        ImageView bookImage;
        LinearLayout parent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.tvBook);
          /*  bookReview = itemView.findViewById(R.id.tvReview);
            readBook = itemView.findViewById(R.id.tvReadBook);*/
            bookImage = itemView.findViewById(R.id.ivBookImage);
            parent = itemView.findViewById(R.id.parentLayout);
        }
    }
}
