package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewsActivity extends AppCompatActivity {
    LinearLayout captnews,jlnews,jbnews,eternalnews;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        captnews = findViewById(R.id.captnews);
        jlnews = findViewById(R.id.jlnews);
        jbnews = findViewById(R.id.jbnews);
        eternalnews = findViewById(R.id.eternalnews);

        getUsernameLocal();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);

        captnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCaptnews = new Intent(NewsActivity.this, NewsDetailActivity.class);
                goCaptnews.putExtra("judul_news","Capt_news");
                startActivity(goCaptnews);
            }
        });
        jlnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goJlnews = new Intent(NewsActivity.this, NewsDetailActivity.class);
                goJlnews.putExtra("judul_news","jl_news");
                startActivity(goJlnews);
            }
        });
        jbnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goJbnews = new Intent(NewsActivity.this, NewsDetailActivity.class);
                goJbnews.putExtra("judul_news","Jb_news");
                startActivity(goJbnews);
            }
        });
        eternalnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goEternalnews = new Intent(NewsActivity.this, NewsDetailActivity.class);
                goEternalnews.putExtra("judul_news","Eternal_news");
                startActivity(goEternalnews);
            }
        });
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}