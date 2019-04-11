package com.example.digitalnoticeboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.StrictMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Button loadWebView, loadNative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // initiate buttons and a web view
        loadWebView = (Button) findViewById(R.id.loadWebView);
        loadWebView.setOnClickListener(this);
        loadNative = (Button) findViewById(R.id.loadNative);
        loadNative.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadWebView:
                intent = new Intent(this, WebViewDemoActivity.class);
                break;
            case R.id.loadNative:
                intent = new Intent(this, NativeHomeActivity.class);
                break;
        }
        finish();
        startActivity(intent);
    }
}
