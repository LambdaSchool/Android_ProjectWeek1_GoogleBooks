package com.example.jacob.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
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

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, bookContent;
        ImageView imageView;
        ViewGroup parentView;
        Spinner spinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_search_element);
            bookTitle = itemView.findViewById(R.id.text_search_element_title);
            bookContent = itemView.findViewById(R.id.text_search_element_content);
            spinner = itemView.findViewById(R.id.spinner_search_element);
            parentView = itemView.findViewById(R.id.search_element_parent_layout);
        }
    }

    private ArrayList<Book> dataList;
    private Context context;
    private Activity activity;
    private ArrayList<String> spinnerArray;

    SearchListAdapter(ArrayList<Book> dataList, Activity activity, ArrayList<String> spinnerArray) {
        this.dataList = dataList;
        this.activity = activity;
        this.spinnerArray = spinnerArray;
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
        viewHolder.bookContent.setText(data.getAuthor());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.spinner.setAdapter(arrayAdapter);
        viewHolder.spinner.setSelection(0, false);
        viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                if (!selectedItem.equals("")) {
                    BooksDbDao.addBook(data);
                    BookshelfDbDao.addBooktoBookshelf(selectedItem, data);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    private File getFileFromCache(String searchText) {
        File file = null;
        String filePath = null;
        File[] items = context.getCacheDir().listFiles();
        for (File item : items) {
            if (item.getName().contains(searchText)) {
                file = item;
                break;
            }
        }
        return file;
    }
}
