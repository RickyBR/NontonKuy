package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Movie_Detail_Activity extends AppCompatActivity {
    Button btn_buy_fix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__detail_);
        btn_buy_fix = findViewById(R.id.btn_buy_fix);

        btn_buy_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBuyTicketFix = new Intent(Movie_Detail_Activity.this, BuyTicketActivity.class);
                startActivity(goBuyTicketFix);
            }
        });
    }
}