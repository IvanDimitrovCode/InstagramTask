package com.example.ivandimitrov.instagramtask.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.ivandimitrov.instagramtask.R;
import com.example.ivandimitrov.instagramtask.retrofit.user.Datum;
import com.example.ivandimitrov.instagramtask.retrofit.user.Images;
import com.example.ivandimitrov.instagramtask.retrofit.user.InstagramImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Dimitrov on 1/31/2017.
 */

public class MediaListAdapter extends ArrayAdapter<Datum> {
    private final Activity mContext;
    private List<Datum> mImageList = new ArrayList<Datum>();
    private Images                  mImage;
    private InstagramImage          mNormalResolutionImage;
    private ImageDataChangeListener mListener;

    public MediaListAdapter(Activity context, List<Datum> itemName, ImageDataChangeListener listener) {
        super(context, R.layout.activity_user_images, itemName);
        this.mContext = context;
        this.mImageList = itemName;
        mListener = listener;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        mImage = mImageList.get(position).getImages();
        mNormalResolutionImage = mImage.getStandardResolution();
        String imageURL = mNormalResolutionImage.getUrl();

        if (view == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.card_element, null, true);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            if (!mListener.containsElement(imageURL)) {
                mListener.onImageAdd(imageURL, null);
            }
            viewHolder.image.setImageBitmap(mListener.getElement(imageURL));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.image.setImageBitmap(mListener.getElement(imageURL));
        return view;
    }

    static class ViewHolder {
        ImageView image;
    }

    public interface ImageDataChangeListener {
        void onImageAdd(String key, Bitmap value);

        void onImageSet(String key, Bitmap value);

        Bitmap getElement(String key);

        boolean containsElement(String url);
    }
}
