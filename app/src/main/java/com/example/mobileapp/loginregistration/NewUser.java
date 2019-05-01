package com.example.mobileapp.loginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewUser extends AppCompatActivity {

    private EditText firstName, lastName, phone, email, pass1, pass2;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        getSupportActionBar().hide();

        firstName = findViewById(R.id.txtFirstName);
        lastName = findViewById(R.id.txtLastName);
        phone = findViewById(R.id.txtPhoneNum);
        email = findViewById(R.id.txtEmail);
        pass1 = findViewById(R.id.txtPassword);
        pass2 = findViewById(R.id.txtPasswordCheck);


        Button btCreate = (Button) findViewById(R.id.btCreateNewAcc);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password1 = pass1.getText().toString();
                String password2 = pass2.getText().toString();
                if(password1.equals(password2)){
                    Intent mainIntent = new Intent(NewUser.this, LoginActivity.class);
                    startActivity(mainIntent);

                    String fname = firstName.getText().toString();
                    String lname = lastName.getText().toString();
                    String cellPhone = phone.getText().toString();
                    String userEmail = email.getText().toString();
                    String pass = pass1.getText().toString();

                    DatabaseReference appointmentRef = database.getReference("hairstylists");
                    appointmentRef.push().setValue(new setUser(fname, lname, cellPhone, userEmail, pass));
                }else{
                    Toast.makeText(NewUser.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

