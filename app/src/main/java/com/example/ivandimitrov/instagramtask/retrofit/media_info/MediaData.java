package com.example.ivandimitrov.instagramtask.retrofit.media_info;

/**
 * Created by Ivan Dimitrov on 2/3/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaData {
    @SerializedName("likes")
    @Expose
    private Likes likes;

    @SerializedName("created_time")
    @Expose
    private String createdTime;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("videos")
    @Expose
    private Videos videos;

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }
}
