package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoviesActivity extends AppCompatActivity {
    Button btn_buy_joker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        btn_buy_joker = findViewById(R.id.btn_buy_joker);

        btn_buy_joker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBuyJoker = new Intent(MoviesActivity.this, Movie_Detail_Activity.class);
                startActivity(goBuyJoker);
            }
        });
    }
}