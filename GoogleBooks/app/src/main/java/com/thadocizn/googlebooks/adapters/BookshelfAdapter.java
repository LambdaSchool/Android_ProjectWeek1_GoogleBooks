package com.thadocizn.googlebooks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.bookInfo.BookViewModel;
import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;

import java.util.ArrayList;
import java.util.List;

public class BookshelfAdapter extends RecyclerView.Adapter<BookshelfAdapter.MyViewHolder> {

    private ArrayList<Bookshelf> bookshelfList;

    public BookshelfAdapter(ArrayList<Bookshelf> bookshelfList) {
        this.bookshelfList = bookshelfList;
    }

    @NonNull
    @Override
    public BookshelfAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_bookshelf, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookshelfAdapter.MyViewHolder myViewHolder, int i) {

        final Bookshelf bookshelf = bookshelfList.get(i);
        myViewHolder.bookshelfName.setText(bookshelf.getName());
        myViewHolder.addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO decide on what I want to use
                BookViewModel viewModel = new BookViewModel();
            }
        });

        myViewHolder.viewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo decide on what I want to use
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bookshelfList == null){
            return 0;
        }else {
            return bookshelfList.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookshelfName;
        Button viewBooks;
        Button   addBook;

        public MyViewHolder(@NonNull View item) {
            super(item);
            bookshelfName = item.findViewById(R.id.tvBookshelfName);
            viewBooks     = item.findViewById(R.id.btnViewBooks);
            addBook       = item.findViewById(R.id.btnAddBook);
        }
    }
}
