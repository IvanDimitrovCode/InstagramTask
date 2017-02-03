package com.example.ivandimitrov.instagramtask.retrofit.media_info;

/**
 * Created by Ivan Dimitrov on 2/3/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {
    @SerializedName("count")
    @Expose
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
