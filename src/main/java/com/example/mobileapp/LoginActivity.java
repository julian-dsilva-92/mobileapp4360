package com.example.mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity{


    // UI references.
    private EditText mPassword, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmail = (EditText) findViewById(R.id.txtEmail);
        mPassword = (EditText) findViewById(R.id.txtPassword);

        Button btLogin = (Button) findViewById(R.id.btLogin );
        btLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if(email.equals("abd@gmail.com") && password.equals("test")){
                    Intent mainIntent = new Intent(LoginActivity.this, NewUser.class);
                    startActivity(mainIntent);
                }else{
                    Toast.makeText(LoginActivity.this, "Name or Password is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void newUser(View view){
        Intent mainIntent = new Intent(this, NewUser.class);
        startActivity(mainIntent);
    }

}

