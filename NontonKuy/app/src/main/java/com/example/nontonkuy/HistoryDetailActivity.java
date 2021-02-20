package com.example.nontonkuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HistoryDetailActivity extends AppCompatActivity {
    ImageView film_photo;
    TextView nama_film,location_tickets,time_tickets,totalprice,total_tickets,no_cinema;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        film_photo = findViewById(R.id.film_photo);
        nama_film = findViewById(R.id.xjudul_film);
        location_tickets = findViewById(R.id.xlocation_ticket);
        time_tickets = findViewById(R.id.xtime_ticket);
        total_tickets = findViewById(R.id.xtotal_tickets);
        totalprice = findViewById(R.id.xtotalprice);
        no_cinema = findViewById(R.id.xno_cinema);

        //get data from intent to this page
        Bundle bundle = getIntent().getExtras();
        final String getJudulFilm = bundle.getString("judulfilm");

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Film").child(getJudulFilm);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            nama_film.setText(dataSnapshot.child("nama_film").getValue().toString());
            location_tickets.setText(dataSnapshot.child("lokasi_film").getValue().toString());
            time_tickets.setText(dataSnapshot.child("waktu_film").getValue().toString());
            no_cinema.setText(dataSnapshot.child("cinema").getValue().toString());
                Picasso.with(HistoryDetailActivity.this).load(dataSnapshot.child("url_photo")
                        .getValue().toString()).centerCrop().fit().into(film_photo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}