package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Color;
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

public class EditBookDialogFragment extends Fragment {
    private Button deleteButton, addToBookshelfButton;
    private CheckBox setHasReadCheckBox;
    private BookVolume bookVolume;

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
        bookVolume = getArguments().getParcelable("book");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(bookVolume.isHasRead() == 1){
            setHasReadCheckBox.setChecked(true);
        }else{
            setHasReadCheckBox.setChecked(false);
        }
        setHasReadCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setHasReadCheckBox.isChecked()){
                    bookVolume.setHasRead(1);
                    BookVolumeViewModel.updateBook(bookVolume);
                    Log.i("onClickCheckBock", "book read update to true");
                }else{
                    bookVolume.setHasRead(0);
                    BookVolumeViewModel.updateBook(bookVolume);
                    Log.i("onClickCheckBock", "book read update to false");
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookVolumeViewModel.deleteBook(bookVolume);
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
