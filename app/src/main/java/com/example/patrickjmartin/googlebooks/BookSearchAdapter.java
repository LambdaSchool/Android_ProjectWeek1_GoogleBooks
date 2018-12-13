package com.example.patrickjmartin.googlebooks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioRecord;
import android.media.Image;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrickjmartin.googlebooks.apiaccess.NetworkAdapter;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BookSearchAdapter extends RecyclerView.Adapter<BookSearchAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout cardView;
        private ImageView bookImage;
        private TextView bookDetails;
        private ProgressBar progressBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.parent_view_searched_books);
            bookImage = itemView.findViewById(R.id.searched_book_image);
            bookDetails = itemView.findViewById(R.id.searched_book_details);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private ArrayList<Book> searchedBooks;
    private ArrayList<Book> chosenBooks = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        final Book b = searchedBooks.get(i);

        final String url = b.getImage();

        if(b.isSelected()){
            holder.cardView.setBackgroundColor(Color.MAGENTA);
        } else {
            holder.cardView.setBackground(null);
        }

        if(b.getImage() != null) {
            new DownloadBookImageTask(holder.bookImage, holder.progressBar).execute(url);
        }

        final String text = "Title: " + b.getTitle() +
                "\nAuthor: " + b.getAuthor() +
                "\nPublish Date: " + b.getPublishDate();
        holder.bookDetails.setText(text);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b.isSelected()) {
                    b.setSelected(false);
                    chosenBooks.remove(b);
                    Toast.makeText(context, "Current picked count: " + chosenBooks.size(), Toast.LENGTH_SHORT).show();
                } else {
                    b.setSelected(true);
                    chosenBooks.add(b);
                    Toast.makeText(context, "Current picked count: " + chosenBooks.size(), Toast.LENGTH_SHORT).show();
                }

                //notifyDataSetChanged();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });



    }

    @Override
    public int getItemCount() {
        return searchedBooks.size();
    }

    private class DownloadBookImageTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewWeakReference;
        ProgressBar progressBar;


        public DownloadBookImageTask(ImageView bookImageView, ProgressBar progressBar) {
            this.imageViewWeakReference = new WeakReference<ImageView>(bookImageView);
            this.progressBar = progressBar;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);


        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];//.replace("http:", "https:");
            Bitmap image = null;
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
            progressBar.setVisibility(View.GONE);
            ImageView imageView = imageViewWeakReference.get();
            imageView.setImageBitmap(bitmap);


        }
    }

    public ArrayList<Book> getChosenBooks(){
        return chosenBooks;
    }

    public void clearChosenBooks(){
        chosenBooks.clear();
    }
}
