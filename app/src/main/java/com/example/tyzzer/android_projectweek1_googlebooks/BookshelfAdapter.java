package com.example.tyzzer.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BookshelfAdapter extends RecyclerView.Adapter<BookshelfAdapter.ViewHolder> {
    public static final String BOOKSHELF_KEY = "bookshelf";


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookshelfTitle;
        ViewGroup parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookshelfTitle = itemView.findViewById(R.id.bookshelf_title);
            parentView = itemView.findViewById(R.id.bookshelf_element_layout);
        }
    }

    private ArrayList<Bookshelf> dataList;
    private ArrayList<Bookshelf> shelfList = new ArrayList<>();
    private Context context;
    private Activity activity;

    BookshelfAdapter(ArrayList<Bookshelf> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BookshelfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bookshelf_element_layout,
                        viewGroup,
                        false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Bookshelf data = dataList.get(i);

        viewHolder.bookshelfTitle.setText(data.getName());
        viewHolder.bookshelfTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookInShelfActivity.class);
                intent.putExtra(BOOKSHELF_KEY, data.getName());
                activity.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
