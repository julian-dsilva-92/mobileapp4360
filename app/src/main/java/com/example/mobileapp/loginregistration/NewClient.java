package com.example.mobileapp.loginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobileapp.R;
import com.example.mobileapp.dashboard.ContactList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewClient extends AppCompatActivity {

    private EditText fitName, laName, phoney, emaily, notesy;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        getSupportActionBar().hide();


      /*  Button btWithImage = (Button) findViewById(R.id.btNewClient);
        btWithImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mainIntent = new Intent(NewClient.this, NewUser.class);
            }
        });

*/

        Button btNoImage = (Button) findViewById(R.id.btNewClient);
        btNoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fitName = findViewById(R.id.txtFirstName);
                laName = findViewById(R.id.txtLastName);
                phoney = findViewById(R.id.txtPhoneNum);
                emaily = findViewById(R.id.txtEmail);
                notesy = findViewById(R.id.txtClientNotes);


                String firstName = fitName.getText().toString();
                String lastName = laName.getText().toString();
                String phone = phoney.getText().toString();
                String email = emaily.getText().toString();
                String notes = notesy.getText().toString();
                String hairstylistID = "hairstylist1";

                DatabaseReference appointmentRef = database.getReference("customers");
                appointmentRef.push().setValue(new setClient(firstName, lastName, phone, email, notes, hairstylistID));

                Intent mainIntent = new Intent(NewClient.this, ContactList.class);
                startActivity(mainIntent);

            }
        });


    }

}
