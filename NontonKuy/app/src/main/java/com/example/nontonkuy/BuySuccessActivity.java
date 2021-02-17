package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuySuccessActivity extends AppCompatActivity {
    Button btn_buy_succes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_success);
        btn_buy_succes = findViewById(R.id.btn_buy_succes);

        btn_buy_succes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent succeshome = new Intent(BuySuccessActivity.this, HomeActivity.class);
                startActivity(succeshome);
            }
        });
    }
}