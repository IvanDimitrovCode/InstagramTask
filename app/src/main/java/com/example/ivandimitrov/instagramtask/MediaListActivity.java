package com.example.ivandimitrov.instagramtask;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ivandimitrov.instagramtask.Tasks.DownloadImageTask;
import com.example.ivandimitrov.instagramtask.adapters.MediaListAdapter;
import com.example.ivandimitrov.instagramtask.retrofit.user.ApiClient;
import com.example.ivandimitrov.instagramtask.retrofit.user.ApiInterface;
import com.example.ivandimitrov.instagramtask.retrofit.user.Datum;
import com.example.ivandimitrov.instagramtask.retrofit.user.Images;
import com.example.ivandimitrov.instagramtask.retrofit.user.InstagramImage;
import com.example.ivandimitrov.instagramtask.retrofit.user.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan Dimitrov on 1/30/2017.
 */

public class MediaListActivity extends Activity implements DownloadImageTask.DownloadImageListener {
    private final static String API_KEY = "4527845236.e5915a5.7b16f0ee10524e4b8a7e677e7044c3f4";
    private List<Datum>      mDataList;
    private MediaListAdapter mAdapter;
    private DataFragment     mDataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_images);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<UserResponse> call = apiService.getImages(API_KEY);

        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        mDataFragment = new DataFragment();
        mDataFragment.setRetainInstance(true);

        fragmentTransaction.add(mDataFragment, "fragment");
        fragmentTransaction.commit();

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                mDataList = response.body().getData();
                setupList();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
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
        mAdapter = new MediaListAdapter(this, mDataList, mDataFragment);
        final ListView listView = (ListView) this.findViewById(R.id.image_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MediaListActivity.this, SelectedMediaActivity.class);
                Images image;
                InstagramImage normalResolutionImage;

                image = mDataList.get(position).getImages();
                normalResolutionImage = image.getStandardResolution();
                String imageURL = normalResolutionImage.getUrl();

                intent.putExtra("mediaID", mDataList.get(position).getId());
                intent.putExtra("mediaURL", imageURL);
                intent.putExtra("mediaType", mDataList.get(position).getType());

                startActivity(intent);
            }
        });
        listView.setAdapter(mAdapter);
    }

    @Override
    public void onImageDownloadFinished(String image, Bitmap imageMap) {
        mAdapter.notifyDataSetChanged();
    }
}
