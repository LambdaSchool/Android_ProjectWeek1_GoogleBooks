package com.thadocizn.googlebooks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;


public class BookshelfDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private View form = null;

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        form = getActivity().getLayoutInflater().inflate(R.layout.dialog_bookshelf_item, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        return builder.setTitle("Create Bookshelf").setView(form)
                .setPositiveButton(android.R.string.ok, this)
                .setNegativeButton(android.R.string.cancel, null).create();
    }
}
