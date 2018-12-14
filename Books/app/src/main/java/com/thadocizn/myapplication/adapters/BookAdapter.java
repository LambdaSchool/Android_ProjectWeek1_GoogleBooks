package com.thadocizn.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.thadocizn.myapplication.ItemClickListener;
import com.thadocizn.myapplication.R;
import com.thadocizn.myapplication.bookInfo.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<Book> bookList;
    private static ItemClickListener clickListener;
    Context context;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }
    public void setOnClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder myViewHolder, final int i) {
        Book currentBook = bookList.get(i);
        myViewHolder.bookId.setText(currentBook.getBookId());
        myViewHolder.bookTitle.setText(currentBook.getBookTitle());
        myViewHolder.bookReview.setText(currentBook.getBookImageUrl());
        myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // clickListener.onClick(i, -1);

                View popup = LayoutInflater.from(context).inflate(R.layout.activity_edit_book, null, false);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                PopupWindow popupWindow = new PopupWindow(popup, width, height, focusable);
                popupWindow.showAtLocation(popup, Gravity.CENTER, 0, 0);
            }
        });

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
        TextView bookId;
        TextView bookReview;
        TextView readBook;
        ImageView bookImage;
        LinearLayout parent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle  = itemView.findViewById(R.id.tvBook);
            bookReview = itemView.findViewById(R.id.tvReview);
            bookId     = itemView.findViewById(R.id.tvBookId);
            bookImage  = itemView.findViewById(R.id.ivBookImage);
            parent     = itemView.findViewById(R.id.parentLayout);
        }
    }
}

