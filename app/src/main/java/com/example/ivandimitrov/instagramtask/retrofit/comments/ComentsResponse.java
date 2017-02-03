package com.example.ivandimitrov.instagramtask.retrofit.comments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ivan Dimitrov on 2/2/2017.
 */

public class ComentsResponse {
    @SerializedName("data")
    @Expose
    private ArrayList<CommentsData> data = null;

    public ArrayList<CommentsData> getComments() {
        return data;
    }
}
