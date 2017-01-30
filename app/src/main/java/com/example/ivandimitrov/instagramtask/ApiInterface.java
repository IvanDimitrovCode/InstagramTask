package com.example.ivandimitrov.instagramtask;

/**
 * Created by Ivan Dimitrov on 1/30/2017.
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("media/recent/")
    Call<ImagesResponse> getImages(@Query("access_token") String apiKey);
}
