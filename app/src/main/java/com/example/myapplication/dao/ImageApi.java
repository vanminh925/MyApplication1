package com.example.myapplication.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.model.Product;

import java.io.InputStream;
import java.net.URL;

public class ImageApi extends AsyncTask<Void, Void, Void> {
    private String urlStr;
    private ImageView imgView;
    private Bitmap bmp;

    public ImageApi(String urlStr, ImageView context) {
        this.urlStr = urlStr;
        this.imgView = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = null;
            url = new URL(urlStr);
            InputStream in = url.openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if(imgView != null && bmp != null) {
            imgView.setImageBitmap(bmp);
        }else  if (bmp == null){
            imgView.setImageResource(R.drawable.ic_baseline_error_100);
        }
    }
}