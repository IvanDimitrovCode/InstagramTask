package com.example.ivandimitrov.instagramtask;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.ivandimitrov.instagramtask.retrofit.ApiClient;
import com.example.ivandimitrov.instagramtask.retrofit.ApiInterface;
import com.example.ivandimitrov.instagramtask.retrofit.Datum;
import com.example.ivandimitrov.instagramtask.retrofit.ImagesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan Dimitrov on 1/30/2017.
 */

public class UserImagesActivity extends Activity implements DownloadImageTask.DownloadImageListener {
    private final static String API_KEY = "4527845236.e5915a5.7b16f0ee10524e4b8a7e677e7044c3f4";
    private List<Datum>       mImages;
    private CustomListAdapter mAdapter;
    private DataFragment      mDataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_images);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<ImagesResponse> call = apiService.getImages(API_KEY);

        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        mDataFragment = new DataFragment();
        mDataFragment.setRetainInstance(true);

        fragmentTransaction.add(mDataFragment, "fragment");
        fragmentTransaction.commit();

        call.enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                mImages = response.body().getData();
                setupList();
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataFragment.setListener(this);
    }

    private void setupList() {
        mAdapter = new CustomListAdapter(this, mImages, mDataFragment);
        final ListView listView = (ListView) this.findViewById(R.id.image_list);
        listView.setAdapter(mAdapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onImageDownloadFinished(String image, Bitmap imageMap) {
        mAdapter.notifyDataSetChanged();
    }
}
