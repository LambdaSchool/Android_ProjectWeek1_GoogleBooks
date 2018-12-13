package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchBooksListAdapter extends RecyclerView.Adapter<SearchBooksListAdapter.ViewHolder> {
    private ArrayList<BookVolume> bookVolumes;
    private Activity activity;
    private Context context;

    public SearchBooksListAdapter(Activity activity, Context context, ArrayList<BookVolume> bookVolumes) {
        this.bookVolumes = bookVolumes;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchBooksListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.book_search_list_element_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchBooksListAdapter.ViewHolder viewHolder, int i) {
        final BookVolume bookVolume = bookVolumes.get(i);
        String title = bookVolume.getTitle();
        String authors = bookVolume.getAuthors().replaceAll(", $", "");
        String descFull = bookVolume.getDesc();
        String descCut = "";
        if(descFull.length() > 100)
            descCut = descFull.substring(0,75) + "...";
        viewHolder.titleText.setText(title + '\n' + authors + '\n' + descCut);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = NetworkAdapter.httpImageRequest(bookVolume.getImageUrl());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewHolder.imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
        viewHolder.parentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                BooksViewModel.addBook(bookVolume);
                new UsersBooksFragment.getBooksFromDb().execute();
                Toast.makeText(context,
                        bookVolume.getTitle() + " added to your library!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookVolumes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        ImageView imageView;
        CardView parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title_text);
            imageView = itemView.findViewById(R.id.image_view);
            parentView = itemView.findViewById(R.id.parent_view);
        }
    }
}
