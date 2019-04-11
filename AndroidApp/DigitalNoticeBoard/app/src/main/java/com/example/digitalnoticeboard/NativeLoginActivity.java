package com.example.digitalnoticeboard;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import android.os.StrictMode;

public class NativeLoginActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Button buttonLogin, buttonSignup;
    Boolean loginsuccess = true;

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        // initiate buttons and a web view
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        buttonSignup.setOnClickListener(this);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                URL url;
                HttpURLConnection connection = null;
                try {
                    // Create connection
                    //url = new URL("http://10.0.2.2:3000/auth/login");
                    url = new URL("http://ec2-3-17-74-12.us-east-2.compute.amazonaws.com:3000/auth/login");
                    String urlParameters = "username=" + URLEncoder.encode(username.getText().toString(), "UTF-8") + "&password=" + URLEncoder.encode(password.getText().toString(), "UTF-8");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
                    connection.setRequestProperty("Content-Language", "en-US");

                    connection.setUseCaches(false);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    // Send request
                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();

                    //Log.w("PRADIPTA ***********", ""+connection.getResponseCode());
                    if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 299)
                    {
                        InputStream is = connection.getInputStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        String line;
                        StringBuffer response = new StringBuffer();
                        while ((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();
                        //Log.w("PRADIPTA ***********", response.toString());
                        JSONObject loginResult = new JSONObject(response.toString());
                        String role = loginResult.getString("role");
                        //Log.w("PRADIPTA ***********", role);
                        if(role.equalsIgnoreCase("Admin"))
                        {
                            intent = new Intent(this, NativeDashboardAdminActivity.class);
                        }
                        else if(role.equalsIgnoreCase("Teacher"))
                        {
                            intent = new Intent(this, NativeDashboardTeacherActivity.class);
                        }
                        else if(role.equalsIgnoreCase("Student"))
                        {
                            intent = new Intent(this, NativeDashboardStudentActivity.class);
                        }
                        else
                        {
                            intent = new Intent(this, NativeDashboardGeneralActivity.class);
                        }
                    }
                    else {
                        loginsuccess = false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    loginsuccess = false;

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                break;
            case R.id.buttonSignup:
                intent = new Intent(this, NativeSignupActivity.class);
                break;
        }

        if(loginsuccess)
        {
            finish();
            startActivity(intent);
        }
        else
        {
            username.setHintTextColor(Color.RED);
            username.setHint("Invalid Login. Try again.");
            username.setText("");
            password.setText("");
        }
    }
}
