package com.joshuahalvorson.android_projectweek1_googlebooks;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrCodeDialogFragment extends Fragment {

    private ImageView imageView;
    private TextView linkText;

    public QrCodeDialogFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.qr_code_dialog_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.qr_code_image);
        linkText = view.findViewById(R.id.link_text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            BookVolume bookVolume = getArguments().getParcelable("book");
            if (bookVolume != null) {
                String url;
                if(TextUtils.isEmpty(bookVolume.getSaleLink())){
                    url = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords="
                            + bookVolume.getTitle().replaceAll(" ", "+");
                }else{
                    url = bookVolume.getSaleLink();
                }
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix;
                try {
                    bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    imageView.setImageBitmap(bmp);
                    linkText.setText(url);
                } catch (WriterException e) {
                    if (getFragmentManager() != null) {
                        getFragmentManager().popBackStack();
                    }
                    Toast.makeText(getContext(),
                            "QR code could not be generated, link not found",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }
    }
}
