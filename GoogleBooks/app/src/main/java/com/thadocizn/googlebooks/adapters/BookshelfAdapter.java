package com.thadocizn.googlebooks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.bookshelfInfo.Bookshelf;
import com.thadocizn.googlebooks.bookshelfInfo.BookshelfViewModel;

import java.util.ArrayList;

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
        myViewHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                BookshelfViewModel model = new BookshelfViewModel();
                model.deleteBookshelf(bookshelf);
                deleteBookshelf((int) bookshelf.getBookshelfId());
                return false;
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
        return bookshelfList.size();
    }

    void deleteBookshelf(int index){
        bookshelfList.remove(index -1);

        notifyItemRemoved(index);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookshelfName;
        Button   viewBooks;
        LinearLayout parent;

        public MyViewHolder(@NonNull View bookshelf) {
            super(bookshelf);
            bookshelfName = bookshelf.findViewById(R.id.tvBookshelfName);
            parent = bookshelf.findViewById(R.id.parentBookshelfActivity);
        }
    }
}
