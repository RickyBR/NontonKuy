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
import com.squareup.picasso.Picasso;

import java.util.Random;

public class BuyTicketActivity extends AppCompatActivity {
    Button btn_plus,btn_minus,btn_buy_fix;
    TextView total_tickets,totalprice;
    Integer valuetotaltickets = 1;
    Integer valuetotalprice= 0;
    Integer valueticketprice = 35000;
    TextView nama_film,rating_film,director_film,cast_film,location_ticket,time_ticket;
    ImageView film_photo;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    DatabaseReference reference,reference3;
    Integer id_transaksi = new Random().nextInt();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        btn_buy_fix = findViewById(R.id.btn_buy_fix);
        total_tickets = findViewById(R.id.total_tickets);
        totalprice = findViewById(R.id.totalprice);
        nama_film = findViewById(R.id.nama_film);
        rating_film = findViewById(R.id.rating_film);
        director_film = findViewById(R.id.director_film);
        cast_film = findViewById(R.id.cast_film);
        location_ticket = findViewById(R.id.location_tickets);
        time_ticket = findViewById(R.id.time_ticket);
        film_photo = findViewById(R.id.film_photo);


        total_tickets.setText(valuetotaltickets.toString());
        valuetotalprice = valueticketprice * valuetotaltickets;
        totalprice.setText(valuetotalprice.toString());
        getUsernameLocal();

        btn_minus.animate().alpha(0).setDuration(300).start();
        btn_minus.setEnabled(false);

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
                location_ticket.setText(dataSnapshot.child("lokasi_film").getValue().toString());
                time_ticket.setText(dataSnapshot.child("waktu_film").getValue().toString());

                Picasso.with(BuyTicketActivity.this).load(dataSnapshot.child("url_photo")
                        .getValue().toString()).centerCrop().fit().into(film_photo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuetotaltickets+=1;
                total_tickets.setText(valuetotaltickets.toString());

                if(valuetotaltickets > 1){
                    btn_minus.animate().alpha(1).setDuration(300).start();
                    btn_minus.setEnabled(true);
                }
                valuetotalprice = valueticketprice * valuetotaltickets;
                totalprice.setText(valuetotalprice.toString());
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuetotaltickets-=1;
                total_tickets.setText(valuetotaltickets.toString());
                if(valuetotaltickets < 2){
                    btn_minus.animate().alpha(0).setDuration(300).start();
                    btn_minus.setEnabled(false);
                }
                valuetotalprice = valueticketprice * valuetotaltickets;
                totalprice.setText(valuetotalprice.toString());

            }
        });

        btn_buy_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference3 = FirebaseDatabase.getInstance().getReference().child("MyTickets").child(username_key_new)
                        .child(nama_film.getText().toString() + id_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    reference3.getRef().child("id_tiket").setValue(nama_film.getText().toString() + id_transaksi);
                    reference3.getRef().child("nama_film").setValue(nama_film.getText().toString());
                    reference3.getRef().child("lokasi_film").setValue(location_ticket.getText().toString());
                    reference3.getRef().child("waktu_film").setValue(time_ticket.getText().toString());
                    reference3.getRef().child("jumlah_tiket").setValue(total_tickets.getText().toString());
                    reference3.getRef().child("total_harga").setValue(totalprice.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent goSuccesbuy = new Intent(BuyTicketActivity.this, BuySuccessActivity.class);
                startActivity(goSuccesbuy);
            }
        });
        
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}