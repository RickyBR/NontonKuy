package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondSignUpAcitivity extends AppCompatActivity {

    Button btn_gohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sign_up_acitivity);
        btn_gohome = findViewById(R.id.btn_gohome);

        btn_gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gohome = new Intent(SecondSignUpAcitivity.this, HomeActivity.class);
                startActivity(gohome);
            }
        });
    }
}