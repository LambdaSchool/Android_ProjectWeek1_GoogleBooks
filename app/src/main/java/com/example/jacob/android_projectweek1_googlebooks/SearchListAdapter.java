package com.example.jacob.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, bookContent;
        ViewGroup parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.text_search_element_title);
            bookContent = itemView.findViewById(R.id.text_search_element_content);
            parentView = itemView.findViewById(R.id.search_element_parent_layout);
        }
    }

    private ArrayList<Book> dataList;
    private Context context;
    private Activity activity;

   SearchListAdapter(ArrayList<Book> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SearchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(
                viewGroup.getContext())
                .inflate(
                        R.layout.search_element_layout,
                        viewGroup,
                        false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListAdapter.ViewHolder viewHolder, int i) {
       final Book data = dataList.get(i);
       viewHolder.bookTitle.setText(data.getTitle());
//       new getBookImageTask().execute()
       //TODO add other elements
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }




}
