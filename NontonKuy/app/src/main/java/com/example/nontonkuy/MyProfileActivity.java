package com.example.nontonkuy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MyProfileActivity extends AppCompatActivity {
    ImageView pict_user;
    EditText et_name,et_bio,et_password,et_email,et_username;
    Button change_pict,btn_gohome;
    TextView edit_btn,cancel_btn;

    Uri photo_location;
    Integer photo_max = 1;

    DatabaseReference reference;
    StorageReference storage;
    ImageView photo_edit_profile;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        et_name = findViewById(R.id.et_name);
        et_bio = findViewById(R.id.et_bio);
        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        pict_user = findViewById(R.id.pict_user);
        change_pict = findViewById(R.id.change_picture);
        edit_btn = findViewById(R.id.edit_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        btn_gohome = findViewById(R.id.btn_gohome);


        getUsernameLocal();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        storage = FirebaseStorage.getInstance().getReference().child("Photousers").child(username_key_new);

        cancel_btn.setVisibility(View.GONE);
        et_email.setEnabled(false);
        et_username.setEnabled(false);
        et_password.setEnabled(false);
        et_bio.setEnabled(false);
        et_name.setEnabled(false);
        change_pict.animate().alpha(0).setDuration(300).start();
        change_pict.setEnabled(false);


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            et_name.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
            et_bio.setText(dataSnapshot.child("bio").getValue().toString());
            et_email.setText(dataSnapshot.child("email_address").getValue().toString());
            et_username.setText(dataSnapshot.child("username").getValue().toString());
            et_password.setText(dataSnapshot.child("password").getValue().toString());

                Picasso.with(MyProfileActivity.this).load(dataSnapshot.child("photo_profile")
                .getValue().toString()).centerCrop().fit().into(pict_user);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_btn.setVisibility(View.GONE);
                cancel_btn.setVisibility(View.VISIBLE);
                et_email.setEnabled(true);
                et_username.setEnabled(true);
                et_password.setEnabled(true);
                et_bio.setEnabled(true);
                et_name.setEnabled(true);
                change_pict.animate().alpha(1).setDuration(300).start();
                change_pict.setEnabled(true);



            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_btn.setVisibility(View.VISIBLE);
                cancel_btn.setVisibility(View.GONE);
                et_email.setEnabled(false);
                et_username.setEnabled(false);
                et_password.setEnabled(false);
                et_bio.setEnabled(false);
                et_name.setEnabled(false);
                change_pict.animate().alpha(0).setDuration(300).start();
                change_pict.setEnabled(false);


            }
        });

    btn_gohome.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dataSnapshot.getRef().child("nama_lengkap").setValue(et_name.getText().toString());
                    dataSnapshot.getRef().child("bio").setValue(et_bio.getText().toString());
                    dataSnapshot.getRef().child("username").setValue(et_username.getText().toString());
                    dataSnapshot.getRef().child("password").setValue(et_password.getText().toString());
                    dataSnapshot.getRef().child("email_address").setValue(et_email.getText().toString());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            if (photo_location != null){
                StorageReference storageReference1 =
                        storage.child(System.currentTimeMillis() + "." +
                                getFileExtension(photo_location));

                storageReference1.putFile(photo_location)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String uri_photo = uri.toString();
                                        reference.getRef().child("photo_profile").setValue(uri_photo);

                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        // berpindah activity
                                        Intent goHome = new Intent(MyProfileActivity.this,HomeActivity.class);
                                        startActivity(goHome);
                                    }
                                });

                            }
                        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    }
                });
            }
        }
    });
        change_pict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPhoto();
            }
        });

    }
    String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void findPhoto(){
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photo_max);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == photo_max && resultCode == RESULT_OK && data != null && data.getData() != null)
        {

            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(pict_user);

        }

    }





    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }


}