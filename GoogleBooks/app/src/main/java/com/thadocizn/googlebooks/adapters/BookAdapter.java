package com.thadocizn.googlebooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.googlebooks.R;
import com.thadocizn.googlebooks.activities.UpdateBookActivity;
import com.thadocizn.googlebooks.bookInfo.BookClass;
import com.thadocizn.googlebooks.bookInfo.BookViewModel;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    public static final String CURRENT_BOOK = "current_book";
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
        final BookClass currentBook = myBookList.get(i);

        myViewHolder.bookId.setText(currentBook.getBookId());
        myViewHolder.bookTitle.setText(currentBook.getBookTitle());
        myViewHolder.bookReview.setText(currentBook.getBookReview());
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateBookActivity.class);
                intent.putExtra(CURRENT_BOOK, currentBook);
                context.startActivity(intent);
            }
        });
        myViewHolder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               Log.i("tag", "Charles" + v.getId());
                deleteBook((int) currentBook.getBookKeyId());
                BookViewModel model = new BookViewModel();
                model.deleteBook(currentBook);
                return false;
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

    void deleteBook(int index){
        myBookList.remove(index -1);

        notifyItemRemoved(index);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView     bookId;
        TextView     bookTitle;
        TextView     bookReview;
        LinearLayout parentLayout;

        public MyViewHolder(@NonNull View book) {
            super(book);
            bookId       = book.findViewById(R.id.book_bookId);
            bookTitle    = book.findViewById(R.id.book_bookTitle);
            bookReview   = book.findViewById(R.id.book_bookReview);
            parentLayout = book.findViewById(R.id.bookActivity);

        }
    }
}
