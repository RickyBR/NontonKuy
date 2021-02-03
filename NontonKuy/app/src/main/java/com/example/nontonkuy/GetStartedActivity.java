package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class GetStartedActivity extends AppCompatActivity {
    Button btn_signup,btn_signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        btn_signup = findViewById(R.id.btn_signup);
        btn_signin = findViewById(R.id.btn_Signin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignin = new Intent(GetStartedActivity.this , SignInActivity.class);
                startActivity(goSignin);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignup = new Intent(GetStartedActivity.this , FirstSignUpActivity.class);
                startActivity(goSignup);
            }
        });

        
    }
}