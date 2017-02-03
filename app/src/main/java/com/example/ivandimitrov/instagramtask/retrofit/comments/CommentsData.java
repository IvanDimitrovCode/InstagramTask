package com.example.ivandimitrov.instagramtask.retrofit.comments;

/**
 * Created by Ivan Dimitrov on 2/2/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("from")
    @Expose
    private From   from;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("created_time")
    @Expose
    private String createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
