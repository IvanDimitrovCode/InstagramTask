package com.example.ivandimitrov.instagramtask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.ivandimitrov.instagramtask.Tasks.DownloadImageTask;
import com.example.ivandimitrov.instagramtask.adapters.CommentListAdapter;
import com.example.ivandimitrov.instagramtask.retrofit.comments.ComentsResponse;
import com.example.ivandimitrov.instagramtask.retrofit.comments.CommentsData;
import com.example.ivandimitrov.instagramtask.retrofit.media_info.Likes;
import com.example.ivandimitrov.instagramtask.retrofit.media_info.MediaData;
import com.example.ivandimitrov.instagramtask.retrofit.media_info.MediaResponse;
import com.example.ivandimitrov.instagramtask.retrofit.user.ApiClient;
import com.example.ivandimitrov.instagramtask.retrofit.user.ApiInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan Dimitrov on 1/27/2017.
 */

public class SelectedMediaActivity extends Activity implements DownloadImageTask.DownloadImageListener {

    private final static String MEDIA_TYPE_IMAGE = "image";
    private final static String MEDIA_TYPE_VIDEO = "video";

    private final static String API_KEY   = "4527845236.e5915a5.7b16f0ee10524e4b8a7e677e7044c3f4";
    private final static String video_url = "https://scontent.cdninstagram.com/t50.2886-16/16471631_446326038824822_11899022209974272_n.mp4";

    private ArrayList<CommentsData> mCommentsDataList = new ArrayList<>();
    private CommentListAdapter mAdapter;
    private ImageView          mSelectedImage;

    //VIDEO
    private VideoView myVideoView;
    private String    mMediaType;
    private String    mVideoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        mMediaType = bundle.getString("mediaType");
        String mediaID = bundle.getString("mediaID");
        String mediaURL = bundle.getString("mediaURL");
        Log.d("MEDIA URL", mediaURL);

        getImageData(mediaID);
        getCommentsData(mediaID);

        if (mMediaType.equals(MEDIA_TYPE_IMAGE)) {
            setContentView(R.layout.activity_image_selected);
            mSelectedImage = (ImageView) findViewById(R.id.selected_image);
            DownloadImageTask downloadImageTask = new DownloadImageTask(this, mediaURL);
            downloadImageTask.execute((Bitmap[]) null);

        } else if (mMediaType.equals(MEDIA_TYPE_VIDEO)) {
            setContentView(R.layout.activity_video_selected);
            myVideoView = (VideoView) findViewById(R.id.video_view);
        }
        getImageData(mediaID);
        getCommentsData(mediaID);

        ImageView imageLikes = (ImageView) findViewById(R.id.image_likes);
        ImageView imageDate = (ImageView) findViewById(R.id.image_date);
        imageLikes.setImageResource(R.drawable.image_likes);
        imageDate.setImageResource(R.drawable.image_date);
    }

    private void getCommentsData(String imageID) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<ComentsResponse> call = apiService.getComments(imageID, API_KEY);
        call.enqueue(new Callback<ComentsResponse>() {
            @Override
            public void onResponse(Call<ComentsResponse> call, Response<ComentsResponse> response) {
                mCommentsDataList = response.body().getComments();
                setupList();
            }

            @Override
            public void onFailure(Call<ComentsResponse> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }
        });
    }

    private void getImageData(String imageID) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<MediaResponse> call = apiService.getLikes(imageID, API_KEY);
        call.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                MediaData mediaData = response.body().getLikes();
                Likes likes = mediaData.getLikes();
                String time = mediaData.getCreatedTime();
                long yourSeconds = Long.parseLong(time);
                Date date = new Date(yourSeconds * 1000);
                DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm");

                TextView likesView = (TextView) findViewById(R.id.likes_count);
                TextView dateView = (TextView) findViewById(R.id.image_date_created);

                likesView.setText(likes.getCount().toString());
                dateView.setText(df.format(date));

                if (mMediaType.equals(MEDIA_TYPE_VIDEO)) {
                    mVideoURL = mediaData.getVideos().getStandardResolution().getUrl();
                    MediaController mediaController = new MediaController(SelectedMediaActivity.this);
                    mediaController.setAnchorView(myVideoView);
                    Uri video = Uri.parse(mVideoURL);
                    myVideoView.setMediaController(mediaController);
                    myVideoView.setVideoURI(video);
                    myVideoView.start();
                }
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }
        });
    }

    private void setupList() {
        mAdapter = new CommentListAdapter(this, mCommentsDataList);
        ListView commentsListView = (ListView) findViewById(R.id.comment_list);
        commentsListView.setAdapter(mAdapter);
    }

    @Override
    public void onImageDownloadFinished(String image, Bitmap imageMap) {
        mSelectedImage.setImageBitmap(imageMap);
    }
}
