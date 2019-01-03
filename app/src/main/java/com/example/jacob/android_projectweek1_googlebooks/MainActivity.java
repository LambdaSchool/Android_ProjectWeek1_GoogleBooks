package com.example.jacob.android_projectweek1_googlebooks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Context context;
    EditText editTextSearch;
    ProgressBar progressBar;
    private ArrayList<Book> booksList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    SearchListAdapter listAdapter;
    ArrayList<String> bookshelfTitles;
    private FirebaseAuth mAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    Intent intent = new Intent(context, BookshelvesActivity.class);
                    startActivity(intent);
                    return true;
/*                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    Constants.RC_SIGN_IN);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        context = this;

        BooksDbDao.initializeInstance(context);

        editTextSearch = findViewById(R.id.edit_text_search);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getBooksTask().execute(editTextSearch.getText().toString());
            }
        });

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bookshelfTitles = new ArrayList<>();
        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new SearchListAdapter(booksList, this);
        recyclerView.setAdapter(listAdapter);
        updateSpinnerList();

//        Intent intent = new Intent(context, GoogleSignInActivity.class);
//        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }

    }

    public class getBooksTask extends AsyncTask<String, Integer, ArrayList<Book>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            booksList.clear();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);
            progressBar.setVisibility(View.GONE);
            booksList.addAll(books);
            listAdapter.notifyDataSetChanged();
            for (int i = 0; i < books.size(); ++i) {
                new getBookImageTask().execute(books.get(i).getImageUrl(), String.valueOf(i));
            }

        }

        @Override
        protected ArrayList<Book> doInBackground(String... strings) {
            return BookGoogleApiDao.searchBooks(strings[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }

    public class getBookImageTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected void onPostExecute(Integer index) {
            super.onPostExecute(index);
            listAdapter.notifyItemChanged(index);
        }


        @Override
        protected Integer doInBackground(String... strings) {
            BookGoogleApiDao.getImageFile(strings[0], context);
            return Integer.parseInt(strings[1]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        updateSpinnerList();
    }

    private void updateSpinnerList() {
        BookshelvesViewModel viewModel;

        viewModel = ViewModelProviders.of(this).get(BookshelvesViewModel.class);
        final Observer<ArrayList<Bookshelf>> observer = new Observer<ArrayList<Bookshelf>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Bookshelf> bookshelvesList) {
                //TODO Make this somehow different so as not to have to get Firebase data like this.
            }
        };
        viewModel.getBookshelvesList().observe(this, observer);
    }
}
