package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersBooksListAdapter extends RecyclerView.Adapter<UsersBooksListAdapter.ViewHolder> {
    private ArrayList<BookVolume> bookVolumes;
    private Activity activity;

    public UsersBooksListAdapter(Activity activity, ArrayList<BookVolume> bookVolumes) {
        this.bookVolumes = bookVolumes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.book_search_list_element_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final BookVolume bookVolume = bookVolumes.get(i);
        String title = bookVolume.getTitle();
        String titleCut = title;
        if(title.length() > 25){
            titleCut = title.substring(0, 22) + "...";
        }
        String review = bookVolume.getUserReview();
        String authors = bookVolume.getAuthors().replaceAll(", $", "");
        String descFull = bookVolume.getDesc();
        String descCut = "";
        if(descFull.length() > 100)
            descCut = descFull.substring(0,50) + "...";
        if(review == null){
            review = "No review yet.";
        }
        viewHolder.titleText.setText(String.format("%s, %s\n%s\nYour review: %s", titleCut, authors, descCut, review));
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
        viewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                EditBookDialogFragment editBookDialogFragment = new EditBookDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("book", bookVolume);
                editBookDialogFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.dialog_container, editBookDialogFragment, "edit_book_dialog_fragment")
                        .addToBackStack(null)
                        .commit();
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
