package com.example.mobileapp.loginregistration;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mobileapp.R;
import com.example.mobileapp.dashboard.dashboard;


public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mPassword, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmail = (EditText) findViewById(R.id.editText);
        mPassword = (EditText) findViewById(R.id.editText3);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if (email.equals("abd@gmail.com") && password.equals("test")) { //if password is correct start dashboard
                   Intent mainIntent = new Intent(LoginActivity.this, dashboard.class);
                    startActivity(mainIntent);

                } else {
                    Toast.makeText(LoginActivity.this, "Name or Password is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void newUser(View view) {
        Intent mainIntent = new Intent(this, NewUser.class);
        startActivity(mainIntent);
    }
}
