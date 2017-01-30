package com.example.ivandimitrov.instagramtask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan Dimitrov on 1/30/2017.
 */

public class ApiClient {
    public static final String   BASE_URL = "https://api.instagram.com/v1/users/self/";
    private static      Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
