package com.example.ivandimitrov.instagramtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = "4527845236.e5915a5.7b16f0ee10524e4b8a7e677e7044c3f4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<ImagesResponse> call = apiService.getImages(API_KEY);
        call.enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                List<Datum> images = response.body().getData();
                for (Datum imageData : images) {
                    Images image = imageData.getImages();
                    InstagramImage normalResolutionImage = image.getStandardResolution();
                    Log.d("ImageID", normalResolutionImage.getUrl());
                }
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("ERROR", t.toString());
            }
        });


//        Button loginButton = (Button) findViewById(R.id.button_login);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, UserActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
