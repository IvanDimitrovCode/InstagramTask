package com.example.ivandimitrov.instagramtask.retrofit.media_info;

/**
 * Created by Ivan Dimitrov on 2/3/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {
    @SerializedName("standard_resolution")
    @Expose
    private VideosStandardResolution standardResolution;

    public VideosStandardResolution getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(VideosStandardResolution standardResolution) {
        this.standardResolution = standardResolution;
    }
}
