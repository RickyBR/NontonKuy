package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashSceenActivity extends AppCompatActivity {

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sceen);
        getUsernameLocal();

    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
        if(username_key_new.isEmpty()){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent goGetStarted = new Intent(SplashSceenActivity.this, GetStartedActivity.class);
                    startActivity(goGetStarted);
                }
            },2000);
        }
        else{
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent goGetStarted = new Intent(SplashSceenActivity.this, HomeActivity.class);
                    startActivity(goGetStarted);
                }
            },2000);
        }
    }
}