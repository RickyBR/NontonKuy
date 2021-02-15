package com.example.nontonkuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    Button btn_buy_fix;
    TextView nama_film,rating_film,director_film,cast_film,sinopsis_film;
    ImageView film_photo;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__detail);
        btn_buy_fix = findViewById(R.id.btn_buy_fix);
        nama_film = findViewById(R.id.judul_film);
        rating_film = findViewById(R.id.rating_film);
        director_film = findViewById(R.id.director_film);
        cast_film = findViewById(R.id.cast_film);
        sinopsis_film = findViewById(R.id.sinopsis_film);
        film_photo = findViewById(R.id.film_photo);

        //get bundle from homepage intent
        Bundle bundle = getIntent().getExtras();
        final String judul_film_baru =bundle.getString("judul_film");

        //get data from firebase by refrence
        reference = FirebaseDatabase.getInstance().getReference().child("Film").child(judul_film_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_film.setText(dataSnapshot.child("nama_film").getValue().toString());
                rating_film.setText(dataSnapshot.child("rating_film").getValue().toString());
                director_film.setText(dataSnapshot.child("director_film").getValue().toString());
                cast_film.setText(dataSnapshot.child("cast_film").getValue().toString());
                sinopsis_film.setText(dataSnapshot.child("sinopsis").getValue().toString());
                Picasso.with(MovieDetailActivity.this).load(dataSnapshot.child("url_photo")
                        .getValue().toString()).centerCrop().fit().into(film_photo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_buy_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBuyTicketFix = new Intent(MovieDetailActivity.this, BuyTicketActivity.class);
                startActivity(goBuyTicketFix);
            }
        });
    }
}