package com.example.nontonkuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    ImageView movies_navbar,akun_navbar,history_navbar,home_navbar;
    TextView name_user,seeallnews,seeallmovie;
    Button btn_joker,btn_endgame,btn_lotr,btn_yourname;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_navbar = findViewById(R.id.home_navbar);
        movies_navbar = findViewById(R.id.movies_navbar);
        akun_navbar = findViewById(R.id.akun_navbar);
        history_navbar = findViewById(R.id.history_navbar);
        name_user = findViewById(R.id.name_user);
        seeallnews = findViewById(R.id.seenews);
        seeallmovie = findViewById(R.id.seeallmovie);
        btn_endgame = findViewById(R.id.btn_endgame);
        btn_joker = findViewById(R.id.btn_joker);
        btn_lotr = findViewById(R.id.btn_lotr);
        btn_yourname = findViewById(R.id.btn_yourname);

        getUsernameLocal();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            name_user.setText(dataSnapshot.child("username").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_endgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent endgamefilm =new Intent(HomeActivity.this, MovieDetailActivity.class);
                endgamefilm.putExtra("judul_film","Endgame");
                startActivity(endgamefilm);
            }
        });
        btn_joker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jokerfilm =new Intent(HomeActivity.this, MovieDetailActivity.class);
                jokerfilm.putExtra("judul_film","Joker");
                startActivity(jokerfilm);
            }
        });
        btn_lotr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lotrfilm =new Intent(HomeActivity.this, MovieDetailActivity.class);
                lotrfilm.putExtra("judul_film","LOTR");
                startActivity(lotrfilm);
            }
        });
        btn_yourname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yournamefilm =new Intent(HomeActivity.this, MovieDetailActivity.class);
                yournamefilm.putExtra("judul_film","Yourname");
                startActivity(yournamefilm);
            }
        });


        home_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(goHomePage);
            }
        });
        movies_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMoviesPage = new Intent(HomeActivity.this, MoviesActivity.class);
                startActivity(goMoviesPage);
            }
        });


        akun_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAkunPage = new Intent(HomeActivity.this, MyProfileActivity.class);
                startActivity(goAkunPage);
            }
        });
        history_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHistoryPage = new Intent(HomeActivity.this , HistoryActivity.class);
                startActivity(goHistoryPage);
            }
        });



        seeallmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goseeMovie = new Intent(HomeActivity.this, MoviesActivity.class);
                startActivity(goseeMovie);
            }
        });
        seeallnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSeeNews = new Intent(HomeActivity.this, NewsActivity.class);
                startActivity(goSeeNews);
            }
        });
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
