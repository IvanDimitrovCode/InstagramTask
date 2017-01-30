package com.example.ivandimitrov.instagramtask;

/**
 * Created by Ivan Dimitrov on 1/30/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("images")
    @Expose
    private Images images;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}