package com.example.ivandimitrov.instagramtask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ivan Dimitrov on 1/27/2017.
 */

public class UserActivity extends Activity {
    private String INSTAGRAM_ACCESS_TOKEN = "4527845236.e5915a5.7b16f0ee10524e4b8a7e677e7044c3f4";
    private String GET_TOKEN_URL          = "https://www.instagram.com/accounts/login/?next=/oauth/authorize/%3Fclient_id%3De5915a5cb5f64bdc86bccf3e800b28c7%26redirect_uri%3Dhttp%3A//nemetschek.bg%26response_type%3Dtoken&force_classic_login=";

    private String GET_INFORMATION_URL = "https://api.instagram.com/v1/users/self/?access_token=" + INSTAGRAM_ACCESS_TOKEN;

    private String GET_MEDIA_URL = "https://api.instagram.com/v1/users/self/media/recent/?access_token=" + INSTAGRAM_ACCESS_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        String node = GET_MEDIA_URL;
        WebView myWebView = (WebView) this.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("TESTERS", "----------" + url);
                view.loadUrl(url);
                return true;
            }
        });
        try {
            myWebView.loadUrl(String.valueOf(new URL(node)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
