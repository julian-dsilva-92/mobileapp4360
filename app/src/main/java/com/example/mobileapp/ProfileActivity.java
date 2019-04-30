package com.example.mobileapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileapp.camera.ImagesActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView clientName;
    private TextView clientDesc;
    private TextView clientNum;
    private Button stylesButton;
    private ImageView imgView;
    String key;

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getIntent().hasExtra("keyValue")) {
            key =  getIntent().getStringExtra("keyValue");}

        //bind data to views
        clientName = findViewById(R.id.profileName);
        clientDesc = findViewById(R.id.profileDescription);
        clientNum = findViewById(R.id.phoneNumber);
        stylesButton = findViewById(R.id.stylesButton);
        imgView = findViewById(R.id.profileImage);


        //fetch customer data from DB and set to object
        DatabaseReference ref = db.getReference("customers/" + key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    //initialize Client object
                    Client client = new Client();
                    
                    client.setFirstName(dataSnapshot.child("firstName").getValue().toString());
                    client.setLastName(dataSnapshot.child("lastName").getValue().toString());
                    client.setDescription(dataSnapshot.child("notes").getValue().toString());
                    client.setPhoneNumber(dataSnapshot.child("phone").getValue().toString());
                    client.setEncodedImage(dataSnapshot.child("profile_pic").getValue().toString());


                    String name = client.getFirstName() + client.getLastName();

                    clientName.setText(name);
                    clientDesc.setText(client.getDescription());
                    clientNum.setText(client.getPhoneNumber());
                    imgView.setImageBitmap(imgDecode(client.getEncodedImage()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //button action
        stylesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //replace with harnoor's styles list page
                startActivity(new Intent(getApplicationContext(), ImagesActivity.class));
            }
        });


    }

    //decode string to image
    public Bitmap imgDecode(final String image) {

        byte[] decodedBytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        return decodedBitmap;
    }


}
