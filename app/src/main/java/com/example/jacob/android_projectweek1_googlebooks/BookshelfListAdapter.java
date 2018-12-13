package com.example.jacob.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BookshelfListAdapter extends RecyclerView.Adapter<BookshelfListAdapter.ViewHolder> {


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, bookContent;
        ImageView imageView;
        View layoutView;
        ViewGroup parentView;
//        Spinner spinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_bookshelf_element);
            bookTitle = itemView.findViewById(R.id.text_bookshelf_element_title);
            bookContent = itemView.findViewById(R.id.text_bookshelf_element_content);
            layoutView = itemView.findViewById(R.id.layout_bookshelf);
            parentView = itemView.findViewById(R.id.bookshelf_element_parent_layout);
        }
    }

    private ArrayList<Book> dataList;
    private Context context;
    private Activity activity;


    BookshelfListAdapter(ArrayList<Book> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BookshelfListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(
                viewGroup.getContext())
                .inflate(
                        R.layout.bookshelf_element_layout,
                        viewGroup,
                        false);
        return new BookshelfListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookshelfListAdapter.ViewHolder viewHolder, int i) {
        final Book data = dataList.get(i);
        viewHolder.bookTitle.setText(data.getTitle());
        viewHolder.bookContent.setText(data.getAuthor());
        viewHolder.layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookViewActivity.class);
                intent.putExtra(Constants.BOOK_EDIT_KEY, data.getId());
                activity.startActivity(intent);
            }
        });

        String imageUrl = data.getImageUrl();
        if (imageUrl != null) {
            String[] urlParts = imageUrl.substring(imageUrl.indexOf("id=") + 3).split("&");
            String fileName = urlParts[0];
            File file = getFileFromCache(fileName);
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                viewHolder.imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                viewHolder.imageView.setImageResource(R.color.colorPrimaryDark);
            } catch (NullPointerException e) {
                viewHolder.imageView.setImageResource(R.color.colorPrimaryDark);
            }
        } else {
            viewHolder.imageView.setImageResource(R.color.colorPrimaryDark);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private File getFileFromCache(String bookshelfText) {
        File file = null;
        File[] items = context.getCacheDir().listFiles();
        for (File item : items) {
            if (item.getName().contains(bookshelfText)) {
                file = item;
                break;
            }
        }
        return file;
        //TODO download image if it has lost its cache.  Somehow should spin that whole thing off on its own somewhere so not repeating code.
    }


}
