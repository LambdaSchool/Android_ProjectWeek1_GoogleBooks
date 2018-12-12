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
import android.widget.CheckBox;

import java.util.ArrayList;

public class EditBookDialogFragment extends Fragment {
    private Button deleteButton, addToBookshelfButton, writeReviewButton, getQrCodeButton;
    private CheckBox setHasReadCheckBox, setFavoriteCheckBox;
    private BookVolume bookVolume;
    private ArrayList<Bookshelf> bookshelves;


    public EditBookDialogFragment(){

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
        return inflater.inflate(R.layout.edit_book_dialog_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        deleteButton = view.findViewById(R.id.delete_book_button);
        addToBookshelfButton = view.findViewById(R.id.start_add_bookshelf_button);
        setHasReadCheckBox = view.findViewById(R.id.set_has_read);
        setFavoriteCheckBox = view.findViewById(R.id.set_favorite);
        writeReviewButton = view.findViewById(R.id.write_review_button);
        getQrCodeButton = view.findViewById(R.id.get_qr_code_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookVolume = getArguments().getParcelable("book");
        bookshelves = BooksViewModel.readBookshelves();
        final Bookshelf favorites = bookshelves.get(0);
        final Bookshelf hasRead = bookshelves.get(1);
        if(bookVolume.isHasRead() == 1){
            setHasReadCheckBox.setChecked(true);
        }else{
            setHasReadCheckBox.setChecked(false);
        }
        if(bookVolume.getIsFavorite() == 1){
            setFavoriteCheckBox.setChecked(true);
        }else{
            setFavoriteCheckBox.setChecked(false);
        }
        setFavoriteCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setFavoriteCheckBox.isChecked()){
                    bookVolume.setIsFavorite(1);
                    BooksViewModel.updateBookIsFavorite(bookVolume);
                    BooksViewModel.addBookshelfBookRelation(favorites, bookVolume);
                    Log.i("onClickCheckBock", "book fav update to true");
                }else{
                    bookVolume.setIsFavorite(0);
                    BooksViewModel.updateBookIsFavorite(bookVolume);
                    BooksViewModel.removeBookshelfBookRelation(favorites, bookVolume);
                    Log.i("onClickCheckBock", "book fav update to false");
                }
            }
        });
        setHasReadCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setHasReadCheckBox.isChecked()){
                    bookVolume.setHasRead(1);
                    BooksViewModel.updateBookHasRead(bookVolume);
                    BooksViewModel.addBookshelfBookRelation(hasRead, bookVolume);
                    Log.i("onClickCheckBock", "book read update to true");
                }else{
                    bookVolume.setHasRead(0);
                    BooksViewModel.updateBookHasRead(bookVolume);
                    BooksViewModel.removeBookshelfBookRelation(hasRead, bookVolume);
                    Log.i("onClickCheckBock", "book read update to false");
                }
            }
        });
        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                EditBookReviewFragment reviewBookFragment = new EditBookReviewFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("bookreview", bookVolume);
                reviewBookFragment.setArguments(bundle);
                ft.add(R.id.dialog_container, reviewBookFragment, "edit_review_fragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        addToBookshelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                AddBookToBookshelfDialogFragment addBookToBookshelfDialogFragment = new AddBookToBookshelfDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("book", bookVolume);
                addBookToBookshelfDialogFragment.setArguments(bundle);
                ft.add(R.id.dialog_container, addBookToBookshelfDialogFragment, "edit_review_fragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        getQrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                QrCodeDialogFragment qrCodeDialogFragment = new QrCodeDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("book", bookVolume);
                qrCodeDialogFragment.setArguments(bundle);
                ft.add(R.id.dialog_container, qrCodeDialogFragment, "qr_code_fragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BooksViewModel.deleteBook(bookVolume);
                Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (currentFragment instanceof UsersBooksFragment) {
                    FragmentTransaction ft = (getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.detach(currentFragment);
                    ft.attach(currentFragment);
                    ft.commit();
                    getFragmentManager().popBackStack();
                }
                Log.i("onClickDeleteButton", "book deleted from sql db");
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
