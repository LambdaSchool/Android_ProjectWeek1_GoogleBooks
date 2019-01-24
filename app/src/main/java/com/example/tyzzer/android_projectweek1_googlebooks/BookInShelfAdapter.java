package com.example.tyzzer.android_projectweek1_googlebooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookInShelfAdapter extends RecyclerView.Adapter<BookInShelfAdapter.ViewHolder> {
    public static final String BOOK_EDIT_KEY = "bookedit";
    Bitmap bitmap = null;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView BiSCover;
        TextView BiSAuthor, BiSTitle;
        ViewGroup parentView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            parentView = itemView.findViewById(R.id.bookinshelf_element_parent_layout);
            BiSCover = itemView.findViewById(R.id.bookinshelf_cover);
            BiSAuthor = itemView.findViewById(R.id.bookinshelf_author);
            BiSTitle = itemView.findViewById(R.id.bookinshelf_title);
        }
    }

    private ArrayList<Book> dataList;
    private String bookshelfTitle;
    private Context context;
    private Activity activity;

    BookInShelfAdapter(ArrayList<Book> dataList, String bookshelfTitle, Activity activity){
        this.dataList = dataList;
        this.bookshelfTitle = bookshelfTitle;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Book data = dataList.get(i);
        final String url = data.getImageUrl();

        viewHolder.BiSTitle.setText(data.getTitle());
        viewHolder.BiSAuthor.setText(data.getAuthor());
        viewHolder.BiSTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetails.class);
                intent.putExtra(BOOK_EDIT_KEY, data.getTitle());
                activity.startActivity(intent);
            }
        });
        if(url != null) {
            new DownloadBookImageTask(viewHolder.BiSCover).execute(url);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from
                (viewGroup.getContext()).inflate(R.layout.bookinshelf_element_layout, viewGroup, false);
        return new BookInShelfAdapter.ViewHolder(view);
    }

    private class DownloadBookImageTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewWeakReference;


        public DownloadBookImageTask(ImageView bookImageView) {
            this.imageViewWeakReference = new WeakReference<>(bookImageView);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap image;
            InputStream in;
            try {
                URL imageURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
                connection.setDoInput(true);
                connection.connect();


                in = connection.getInputStream();
                image = BitmapFactory.decodeStream(in);
                in.close();
                return image;
            } catch (IOException e) {
                e.printStackTrace();
                return  null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imageView = imageViewWeakReference.get();
            imageView.setImageBitmap(bitmap);
        }
    }


}
