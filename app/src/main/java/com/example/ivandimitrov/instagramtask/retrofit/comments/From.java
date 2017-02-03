package com.example.ivandimitrov.instagramtask.retrofit.comments;

/**
 * Created by Ivan Dimitrov on 2/2/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class From {
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("username")
    @Expose
    private String username;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
