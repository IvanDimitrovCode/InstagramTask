package com.example.ivandimitrov.instagramtask.retrofit.media_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan Dimitrov on 2/3/2017.
 */


public class MediaResponse {
    @SerializedName("data")
    @Expose
    private MediaData data = null;

    public MediaData getLikes() {
        return data;
    }
}