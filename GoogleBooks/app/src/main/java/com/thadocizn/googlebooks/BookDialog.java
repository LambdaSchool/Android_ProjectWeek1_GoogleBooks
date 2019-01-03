package com.thadocizn.googlebooks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class BookDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private View form = null;

    private TextView bookId;
    private TextView bookTitle;
    private EditText bookReview;
    private Switch   readBook;

    @Override
    public void onClick(DialogInterface dialog, int which) {
        bookId     = form.findViewById(R.id.tvBookId);
        bookTitle  = form.findViewById(R.id.tvBookTitle);
        bookReview = form.findViewById(R.id.etBookReview);
        readBook   = form.findViewById(R.id.bookReadSwitch);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        form = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_book_item, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Update Book").setView(form)
                .setPositiveButton("Delete", this)
                .setNegativeButton(android.R.string.cancel, null).create();
    }
}
