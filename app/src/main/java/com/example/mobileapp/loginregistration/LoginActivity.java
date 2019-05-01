package com.example.mobileapp.loginregistration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.calendar.newAppt;
import com.example.mobileapp.dashboard.dashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private String email;
    private String password;
    public static String stylistKey;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    // UI references.
    private EditText mPassword, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmail = (EditText) findViewById(R.id.editText);
        mPassword = (EditText) findViewById(R.id.editText3);

        getSupportActionBar().hide();

        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                 email = mEmail.getText().toString();
                 password = mPassword.getText().toString();
                DatabaseReference ref = database.getReference("hairstylists");

                ref.orderByChild("userEmail").equalTo(email).addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists())
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                             String keys = datas.getKey();

                             validateUser(keys, password, email);
                            }
                            else{
                            Toast.makeText(LoginActivity.this, "Name or Password is incorrect", Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }
        });
    }

    public void newUser(View view) {
        Intent mainIntent = new Intent(this, NewUser.class);
        startActivity(mainIntent);
    }

    public void validateUser (String key, final String pass, final String user){

        DatabaseReference ref = database.getReference("hairstylists/" + key);
        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String passCheck = dataSnapshot.child("pass").getValue(String.class);
                if (passCheck.equals(pass)){
                    stylistKey =  user;
                    Intent mainIntent = new Intent(LoginActivity.this, dashboard.class);
                    startActivity(mainIntent);

                }
                else {
                    Toast.makeText(LoginActivity.this, "Name or Password is incorrect", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        }

}
