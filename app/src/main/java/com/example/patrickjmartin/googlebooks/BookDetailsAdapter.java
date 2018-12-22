package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.patrickjmartin.googlebooks.apiaccess.NetworkAdapter;

import java.util.ArrayList;

public class BookDetailsAdapter extends ArrayAdapter<Book> {

    private Context context;
    private Activity activity;
    private ArrayList<Book> booksAList = new ArrayList<>();


    public BookDetailsAdapter(@NonNull Context context, @NonNull Activity activity, ArrayList<Book> aList) {
        super(context, 0, aList);
        this.context = context;
        this.activity = activity;
        this.booksAList = aList;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,  @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.book_details_listview, parent,false);
        }


        final Book currentBook = booksAList.get(position);
        final ImageView image = listItem.findViewById(R.id.book_details_listview_image);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = NetworkAdapter.httpImageRequest(currentBook.getImage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        image.setImageBitmap(bitmap);
                    }
                });

            }
        }).start();

        return listItem;


    }
}
