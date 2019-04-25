package com.example.mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        //query to fetch client information, just add customer_id
        //String query = "SELECT * FROM mobile_app.customers WHERE customer_id = ;";


        Client c = new Client("Jenny", "Choi", "loves black coffee", "404-435-2051");

        clientName = findViewById(R.id.profileName);
        clientDesc = findViewById(R.id.profileDescription);
        clientNum = findViewById(R.id.phoneNumber);
        stylesButton = findViewById(R.id.stylesButton);
        imgView = findViewById(R.id.profileImage);

        String name = c.getFirstName() + " " + c.getLastName();

        clientName.setText(name);
        clientDesc.setText(c.getDescription());
        clientNum.setText(c.getPhoneNumber());
        //set imgView here

        stylesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //replace Client.class with harnoor's styles list page
                startActivity(new Intent(getApplicationContext(), Client.class));
            }
        });




    }



}
