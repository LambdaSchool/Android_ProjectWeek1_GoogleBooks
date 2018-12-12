package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookSearchAdapter extends RecyclerView.Adapter<BookSearchAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView bookImage;
        private TextView bookDetails;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.parent_view_searched_books);
            bookImage = itemView.findViewById(R.id.searched_book_image);
            bookDetails = itemView.findViewById(R.id.searched_book_details);
        }
    }

    private ArrayList<Book> searchedBooks;
    private Context context;
    private Activity activity;

    public BookSearchAdapter(ArrayList<Book> books, Activity activity) {
        this.searchedBooks = books;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.searched_book_layout,
                viewGroup,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final Book b = searchedBooks.get(i);
        //Place code for the imageview
        final String text = "Title: " + b.getTitle() +
                "\nAuthor: " + b.getAuthor() +
                "\nPublish Date: " + b.getPublishDate();
        holder.bookDetails.setText(text);

    }



    @Override
    public int getItemCount() {
        return searchedBooks.size();
    }
}
