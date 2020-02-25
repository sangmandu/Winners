package com.example.winners_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;


public class OpenHttpConnection extends AsyncTask<Object, Void, Bitmap> {

    private ImageView bmImage;
    Bitmap mSaveBm;
    @Override
    protected Bitmap doInBackground(Object... params) {
        Bitmap mBitmap = null;
        bmImage = (ImageView) params[0];
        String url = (String) params[1];
        InputStream in = null;
        try {
            in = new java.net.URL(url).openStream();
            mBitmap = BitmapFactory.decodeStream(in);
            in.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bm) {
        super.onPostExecute(bm);
        mSaveBm = bm;
        bmImage.setImageBitmap(bm);
    }
}