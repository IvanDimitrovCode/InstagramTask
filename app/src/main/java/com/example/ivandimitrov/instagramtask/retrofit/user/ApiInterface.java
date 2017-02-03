package com.example.ivandimitrov.instagramtask.retrofit.user;

/**
 * Created by Ivan Dimitrov on 1/30/2017.
 */

import com.example.ivandimitrov.instagramtask.retrofit.comments.ComentsResponse;
import com.example.ivandimitrov.instagramtask.retrofit.media_info.MediaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("users/self/media/recent/")
    Call<UserResponse> getImages(@Query("access_token") String apiKey);

    @GET("media/{id}/comments?")
    Call<ComentsResponse> getComments(@Path("id") String id, @Query("access_token") String apiKey);

    @GET("media/{id}?")
    Call<MediaResponse> getLikes(@Path("id") String id, @Query("access_token") String apiKey);
}
