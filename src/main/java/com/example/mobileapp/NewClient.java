package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewClient extends AppCompatActivity {

    private EditText firstName, lastName, phone, email, notes;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String keys = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        firstName = findViewById(R.id.txtFirstName);
        lastName = findViewById(R.id.txtLastName);
        phone = findViewById(R.id.txtPhoneNum);
        email = findViewById(R.id.txtEmail);
        notes = findViewById(R.id.txtClientNotes);

        String fname = firstName.getText().toString();
        String lname = lastName.getText().toString();
        String cellPhone = phone.getText().toString();
        String clientEmail = email.getText().toString();
        String specs = notes.getText().toString();
        String stylistID = "hairstylist1";

        DatabaseReference appointmentRef = database.getReference("customers");
        appointmentRef.push().setValue(new setUser(fname, lname, cellPhone, clientEmail, specs, stylistID));


        Button btWithImage = (Button) findViewById(R.id.btNewClient);
        btWithImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(NewClient.this, NewUser.class);
            }
        });



        Button btNoImage = (Button) findViewById(R.id.btNewClient2);
        btNoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(NewClient.this, NewUser.class);

            }
        });


    }

}
