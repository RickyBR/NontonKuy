package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    ImageView movies_navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        movies_navbar = findViewById(R.id.movies_navbar);

        movies_navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMoviesPage = new Intent(HomeActivity.this, MoviesActivity.class);
                startActivity(goMoviesPage);
            }
        });
    }
}