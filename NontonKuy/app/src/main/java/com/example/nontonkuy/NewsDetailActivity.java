package com.example.nontonkuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    ImageView news_image;
    TextView news_caption,news_isi_berita1,news_isi_berita2;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        news_caption = findViewById(R.id.news_caption);
        news_isi_berita1 = findViewById(R.id.news_isi_berita1);
        news_isi_berita2 = findViewById(R.id.news_isi_berita2);
        news_image = findViewById(R.id.news_image);

        //get bundle from newspage intent
        Bundle bundle = getIntent().getExtras();
        final String judul_berita_baru =bundle.getString("judul_news");

        //get from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("News").child(judul_berita_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               news_caption.setText(dataSnapshot.child("judul_news").getValue().toString());
               news_isi_berita1.setText(dataSnapshot.child("isi_berita1").getValue().toString());
               news_isi_berita2.setText(dataSnapshot.child("isi_berita2").getValue().toString());
               Picasso.with(NewsDetailActivity.this).load(dataSnapshot.child("url_photo")
                        .getValue().toString()).centerCrop().fit().into(news_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}