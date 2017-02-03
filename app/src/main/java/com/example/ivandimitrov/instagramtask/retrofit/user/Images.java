package com.example.ivandimitrov.instagramtask.retrofit.user;

/**
 * Created by Ivan Dimitrov on 1/30/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("standard_resolution")
    @Expose
    private InstagramImage standardResolution;
    @SerializedName("low_resolution")
    @Expose
    private InstagramImage lowResolution;
    @SerializedName("thumbnail")
    @Expose
    private InstagramImage thumbnail;

    public InstagramImage getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(InstagramImage standardResolution) {
        this.standardResolution = standardResolution;
    }

    public InstagramImage getLowResolution() {
        return lowResolution;
    }

    public void setLowResolution(InstagramImage lowResolution) {
        this.lowResolution = lowResolution;
    }

    public InstagramImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(InstagramImage thumbnail) {
        this.thumbnail = thumbnail;
    }
}
