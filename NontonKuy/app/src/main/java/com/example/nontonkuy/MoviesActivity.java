package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MoviesActivity extends AppCompatActivity {
    Button btn_buy_joker,btn_buy_endgame,btn_buy_lotr,btn_buy_yourname,btn_buy_spongebob;
    ImageView movies_navbar,akun_navbar,home_navbar,history_navbar;



    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        home_navbar = findViewById(R.id.home_navbar);
        movies_navbar = findViewById(R.id.movies_navbar);
        akun_navbar = findViewById(R.id.akun_navbar);
        history_navbar = findViewById(R.id.history_navbar);
        btn_buy_joker = findViewById(R.id.btn_buy_joker);
        btn_buy_endgame = findViewById(R.id.btn_buy_endgame);
        btn_buy_yourname = findViewById(R.id.btn_buy_yourname);
        btn_buy_spongebob = findViewById(R.id.btn_buy_spongebob);
        btn_buy_lotr = findViewById(R.id.btn_buy_lotr);
        getUsernameLocal();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);

        btn_buy_joker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBuyJoker = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                goBuyJoker.putExtra("judul_film","Joker");
                startActivity(goBuyJoker);
            }
        });
        btn_buy_endgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBuyJoker = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                goBuyJoker.putExtra("judul_film","Endgame");
                startActivity(goBuyJoker);
            }
        });
        btn_buy_spongebob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBuyJoker = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                goBuyJoker.putExtra("judul_film","Spongebob");
                startActivity(goBuyJoker);
            }
        });

        btn_buy_yourname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBuyJoker = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                goBuyJoker.putExtra("judul_film","Yourname");
                startActivity(goBuyJoker);
            }
        });

        btn_buy_lotr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBuyJoker = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                goBuyJoker.putExtra("judul_film","LOTR");
                startActivity(goBuyJoker);
            }
        });
        home_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(MoviesActivity.this, HomeActivity.class);
                startActivity(goHomePage);
            }
        });
        movies_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMoviesPage = new Intent(MoviesActivity.this, MoviesActivity.class);
                startActivity(goMoviesPage);
            }
        });


        akun_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAkunPage = new Intent(MoviesActivity.this, MyProfileActivity.class);
                startActivity(goAkunPage);
            }
        });
        history_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHistoryPage = new Intent(MoviesActivity.this , HistoryActivity.class);
                startActivity(goHistoryPage);
            }
        });
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}