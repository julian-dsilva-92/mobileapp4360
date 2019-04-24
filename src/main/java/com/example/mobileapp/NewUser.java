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

public class NewUser extends AppCompatActivity {

    private EditText firstName, lastName, phone, email, pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

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
                if(pass1.equals(pass2)){
                    Intent mainIntent = new Intent(NewUser.this, dashboard.class);
                    startActivity(mainIntent);
                }else{
                    Toast.makeText(NewUser.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
