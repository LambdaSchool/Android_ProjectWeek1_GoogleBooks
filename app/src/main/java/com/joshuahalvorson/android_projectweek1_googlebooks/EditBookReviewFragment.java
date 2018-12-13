package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditBookReviewFragment extends Fragment{
    private Button submitReview, tweetButton;
    private EditText reviewContent;
    private BookVolume bookVolume;

    public EditBookReviewFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.review_book_dialog_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitReview = view.findViewById(R.id.submit_review_button);
        reviewContent = view.findViewById(R.id.review_content);
        if (getArguments() != null) {
            bookVolume = getArguments().getParcelable("bookreview");
        }
        tweetButton = view.findViewById(R.id.send_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        reviewContent.setText(bookVolume.getUserReview());
        tweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Here is my review of " +
                        bookVolume.getTitle() + ": "  +
                        reviewContent.getText().toString());
                startActivity(intent);
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }

            }
        });
        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reviewText = reviewContent.getText().toString();
                bookVolume.setUserReview(reviewText);
                BooksViewModel.updateBookUserReview(bookVolume);
                new UsersBooksFragment.getBooksFromDb().execute();
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
                Log.i("onClickSubmitReview", "review was saved to sql db");
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
