package com.example.digitalnoticeboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NativeHomeActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Button buttonLogin, buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_home);

        // initiate buttons and a web view
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        buttonSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                intent = new Intent(this, NativeLoginActivity.class);
                break;
            case R.id.buttonSignup:
                intent = new Intent(this, NativeSignupActivity.class);
                break;
        }
        finish();
        startActivity(intent);
    }
}
