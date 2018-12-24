package com.thadocizn.googlebooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.activities.BookshelfActivity;
import com.thadocizn.googlebooks.bookInfo.BookClass;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    public static final String CURRENT_BOOK = "currentBook";
    private ArrayList<BookClass> bookList;
    Context context;
    EditText title;

    public BookAdapter(ArrayList<BookClass> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_activity, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookAdapter.MyViewHolder myViewHolder, final int i) {
        final BookClass currentBook = bookList.get(i);
        myViewHolder.bookId.setText(currentBook.getBookId());
        myViewHolder.bookTitle.setText(currentBook.getBookTitle());
        myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.save.setVisibility(View.VISIBLE);
            }
        });
        myViewHolder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* BookViewModel viewModel = new BookViewModel();
                viewModel.addBook(currentBook);*/

                Intent intent = new Intent(context, BookshelfActivity.class);
                intent.putExtra(CURRENT_BOOK, currentBook);
                context.startActivity(intent);
            }
        });
        //Todo image

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
        Button save;
        LinearLayout parent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.tvBook);
            bookReview = itemView.findViewById(R.id.tvReview);
            bookId = itemView.findViewById(R.id.tvBookId);
            save = itemView.findViewById(R.id.btnSave);
            parent = itemView.findViewById(R.id.parentLayout);
        }
    }
}
