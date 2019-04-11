package com.example.digitalnoticeboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class SampleWebViewActivity extends AppCompatActivity implements View.OnClickListener {

    WebView simpleWebView;
    Button loadWebPage, loadFromStaticHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_web_view);

        // initiate buttons and a web view
        loadFromStaticHtml = (Button) findViewById(R.id.loadFromStaticHtml);
        loadFromStaticHtml.setOnClickListener(this);
        loadWebPage = (Button) findViewById(R.id.loadWebPage);
        loadWebPage.setOnClickListener(this);
        simpleWebView = (WebView) findViewById(R.id.simpleWebView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadFromStaticHtml:
                // define static html text
                String customHtml = "<html><body><h1>Hello, AbhiAndroid</h1>" +
                        "<h1>Heading 1</h1><h2>Heading 2</h2><h3>Heading 3</h3>" +
                        "<p>This is a sample paragraph of static HTML In Web view</p>" +
                        "</body></html>";
                simpleWebView.loadData(customHtml, "text/html", "UTF-8"); // load html string data in a web view
                break;
            case R.id.loadWebPage:
                simpleWebView.setWebViewClient(new SampleWebViewActivity.MyWebViewClient());
                //String url = "https://abhiandroid.com/ui/";
                //String url = "http://localhost:3000/";
                String url = "http://10.0.2.2:3000/";
                simpleWebView.getSettings().setJavaScriptEnabled(true);
                simpleWebView.loadUrl(url); // load a web page in a web view
                break;
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
