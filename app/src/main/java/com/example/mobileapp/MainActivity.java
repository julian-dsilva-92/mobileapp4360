package com.example.mobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView clientName;
    private TextView clientDesc;
    private TextView clientNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Client c = new Client("Jenny", "Choi", "loves black coffee", "404-435-2051");

        clientName = findViewById(R.id.profileName);
        clientDesc = findViewById(R.id.profileDescription);
        clientNum = findViewById(R.id.phoneNumber);

        String name = c.getFirstName() + " " + c.getLastName();

        clientName.setText(name);
        clientDesc.setText(c.getDescription());
        clientNum.setText(c.getPhoneNumber());




    }



}
