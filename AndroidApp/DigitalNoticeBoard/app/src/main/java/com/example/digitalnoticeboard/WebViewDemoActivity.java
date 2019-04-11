package com.example.digitalnoticeboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewDemoActivity extends AppCompatActivity {

    WebView simpleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);
        simpleWebView = (WebView) findViewById(R.id.simpleWebView);
        simpleWebView.setWebViewClient(new WebViewDemoActivity.MyWebViewClient());
        //String url = "https://abhiandroid.com/ui/";
        //String url = "http://localhost:3000/";
        //String url = "http://10.0.2.2:3000/";
        String url = "http://ec2-3-17-74-12.us-east-2.compute.amazonaws.com:3000/";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load a web page in a web view
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
