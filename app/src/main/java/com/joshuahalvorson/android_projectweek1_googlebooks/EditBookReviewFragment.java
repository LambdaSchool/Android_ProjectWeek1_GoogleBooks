package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditBookReviewFragment extends Fragment{
    private Button submitReview;
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
        bookVolume = getArguments().getParcelable("bookreview");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        reviewContent.setText(bookVolume.getUserReview());
        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reviewText = reviewContent.getText().toString();
                bookVolume.setUserReview(reviewText);
                BooksViewModel.updateBookUserReview(bookVolume);
                Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (currentFragment instanceof UsersBooksFragment) {
                    FragmentTransaction ft = (getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.detach(currentFragment);
                    ft.attach(currentFragment);
                    ft.commit();
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
