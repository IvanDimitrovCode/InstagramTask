package com.example.ivandimitrov.instagramtask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ivan Dimitrov on 2/2/2017.
 */

public class DownloadImageTask extends AsyncTask<Bitmap, Bitmap, Bitmap> {
    private DownloadImageListener mListener;
    private String                mUrl;

    DownloadImageTask(DownloadImageListener listener, String url) {
        this.mListener = listener;
        this.mUrl = url;
    }

    @Override
    protected Bitmap doInBackground(Bitmap... params) {
        Bitmap map = null;
        try {
            URL url = new URL(mUrl);
            map = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    protected void onPostExecute(Bitmap map) {
        super.onPostExecute(map);
        mListener.onImageDownloadFinished(mUrl, map);
    }

    interface DownloadImageListener {
        void onImageDownloadFinished(String image, Bitmap imageMap);
    }
}
