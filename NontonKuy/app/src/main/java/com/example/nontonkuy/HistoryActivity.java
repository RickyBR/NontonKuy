package com.example.nontonkuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView myticket_set;
    DatabaseReference reference,reference2;
    ArrayList<HistoryTicket> list;
    HistoryAdapter historyAdapter;
    ImageView home_navbar,movies_navbar,akun_navbar,history_navbar;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        home_navbar = findViewById(R.id.home_navbar);
        movies_navbar = findViewById(R.id.movies_navbar);
        akun_navbar = findViewById(R.id.akun_navbar);
        history_navbar = findViewById(R.id.history_navbar);
        myticket_set = findViewById(R.id.myticket_set);
        myticket_set.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<HistoryTicket>();
        getUsernameLocal();

        reference = FirebaseDatabase.getInstance().getReference().child("MyTickets").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    HistoryTicket p  = dataSnapshot1.getValue(HistoryTicket.class);
                    list.add(p);
                }
                historyAdapter = new HistoryAdapter(HistoryActivity.this, list);
                myticket_set.setAdapter(historyAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        home_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(HistoryActivity.this, HomeActivity.class);
                startActivity(goHomePage);
            }
        });
        movies_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMoviesPage = new Intent(HistoryActivity.this, MoviesActivity.class);
                startActivity(goMoviesPage);
            }
        });

        akun_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAkunPage = new Intent(HistoryActivity.this, MyProfileActivity.class);
                startActivity(goAkunPage);
            }
        });
        history_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHistoryPage = new Intent(HistoryActivity.this , HistoryActivity.class);
                startActivity(goHistoryPage);
            }
        });

    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}