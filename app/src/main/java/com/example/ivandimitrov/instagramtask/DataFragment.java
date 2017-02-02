package com.example.ivandimitrov.instagramtask;

import android.app.Fragment;
import android.graphics.Bitmap;

import java.util.HashMap;

/**
 * Created by Ivan Dimitrov on 2/2/2017.
 */

public class DataFragment extends Fragment implements CustomListAdapter.ImageDataChangeListener, DownloadImageTask.DownloadImageListener {
    private HashMap<String, Bitmap> imageList = new HashMap<>();
    private DownloadImageTask.DownloadImageListener mListener;

    public void onImageAdd(String key, Bitmap value) {
        imageList.put(key, value);
        DownloadImageTask downloadImageTask = new DownloadImageTask(this, key);
        downloadImageTask.execute(value);
    }

    public Bitmap getElement(String key) {
        return imageList.get(key);
    }

    public boolean containsElement(String key) {
        return imageList.containsKey(key);
    }

    public void onImageSet(String key, Bitmap value) {
        imageList.put(key, value);
    }

    @Override
    public void onImageDownloadFinished(String image, Bitmap imageMap) {
        onImageSet(image, imageMap);
        mListener.onImageDownloadFinished(image, imageMap);
    }

    public void setListener(DownloadImageTask.DownloadImageListener listener) {
        mListener = listener;
    }
}
