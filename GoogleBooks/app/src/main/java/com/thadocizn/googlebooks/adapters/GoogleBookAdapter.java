package com.thadocizn.googlebooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.activities.BookActivity;
import com.thadocizn.googlebooks.bookInfo.BookClass;
import com.thadocizn.googlebooks.bookInfo.BookViewModel;

import java.util.ArrayList;

public class GoogleBookAdapter extends RecyclerView.Adapter<GoogleBookAdapter.MyViewHolder> {

    public static final String CURRENT_BOOK = "currentBook";
    private ArrayList<BookClass> bookList;
    Context context;

    public GoogleBookAdapter(ArrayList<BookClass> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public GoogleBookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {

        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoogleBookAdapter.MyViewHolder myViewHolder, final int i) {
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
                BookViewModel model = new BookViewModel();
                model.addBook(currentBook);

                /*Intent intent = new Intent(context, BookActivity.class);
                context.startActivity(intent);*/
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
