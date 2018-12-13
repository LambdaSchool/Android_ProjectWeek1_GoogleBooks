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
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrCodeDialogFragment extends Fragment {

    private ImageView imageView;

    public QrCodeDialogFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.qr_code_dialog_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.qr_code_image);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BookVolume bookVolume = null;
        if (getArguments() != null) {
            bookVolume = getArguments().getParcelable("book");
        }
        if (bookVolume != null) {
            if(!TextUtils.isEmpty(bookVolume.getSaleLink())){
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix;
                try {
                    String saleLink = bookVolume.getSaleLink();
                    bitMatrix = writer.encode(saleLink, BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    imageView.setImageBitmap(bmp);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }else{
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
                Toast.makeText(getContext(),
                        "QR code could not be generated, buy link not found",
                        Toast.LENGTH_LONG).show();

            }
        }
    }
}
