package com.example.ivandimitrov.instagramtask.Tasks;

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

    public DownloadImageTask(DownloadImageListener listener, String url) {
        this.mListener = listener;
        this.mUrl = url;
    }

    @Override
    public Bitmap doInBackground(Bitmap... params) {
        Bitmap map = null;
        try {
            URL url = new URL(mUrl);
            map = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void onPostExecute(Bitmap map) {
        super.onPostExecute(map);
        mListener.onImageDownloadFinished(mUrl, map);
    }

    public interface DownloadImageListener {
        void onImageDownloadFinished(String image, Bitmap imageMap);
    }
}
