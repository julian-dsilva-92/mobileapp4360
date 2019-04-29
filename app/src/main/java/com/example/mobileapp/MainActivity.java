package com.example.mobileapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView clientName;
    private TextView clientDesc;
    private TextView clientNum;
    private Button stylesButton;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fetch customer data from DB and set to object below

        Client c = new Client("Jenny", "Choi", "loves black coffee", "404-435-2051", "dkfjdkf");

        //bind data to views
        clientName = findViewById(R.id.profileName);
        clientDesc = findViewById(R.id.profileDescription);
        clientNum = findViewById(R.id.phoneNumber);
        stylesButton = findViewById(R.id.stylesButton);
        imgView = findViewById(R.id.profileImage);

        //concatenate first & last name
        String name = c.getFirstName() + " " + c.getLastName();

        //set data
        clientName.setText(name);
        clientDesc.setText(c.getDescription());
        clientNum.setText(c.getPhoneNumber());
        imgView.setImageBitmap(imgDecode(c.getEncodedImage()));


        //button action
        stylesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //replace with harnoor's styles list page
                startActivity(new Intent(getApplicationContext(), Client.class));
            }
        });




    }

    //decode string to image
    public Bitmap imgDecode(final String image) {
        String encodedImage = "";

        byte[] decodedBytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        return decodedBitmap;
    }


}
