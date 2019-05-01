package com.example.mobileapp.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.calendar.MainCalendarActivity;
import com.example.mobileapp.camera.CameraActivity;
import com.example.mobileapp.dB.firebaseDashboard;
import com.example.mobileapp.loginregistration.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class dashboard extends AppCompatActivity {

    private RecyclerView mrecyclerview;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        getSupportActionBar().hide();

        mrecyclerview = (RecyclerView) findViewById(R.id.bookrecyclerview);
        new firebaseDashboard().getdashboarddata(new firebaseDashboard.getallAppts() {
            @Override
            public void Dataisloaded(List<getApptDashboard> appts) {
                new recylerviewconfig().setconfig(mrecyclerview, dashboard.this, appts);
            }
        });


        DatabaseReference ref = database.getReference("hairstylists");

        ref.orderByChild("userEmail").equalTo(LoginActivity.stylistKey).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    String keys = datas.getKey();

                    getDashboardname(keys);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void ContactList(View v) {
        Intent con = new Intent(this, ContactList.class);
        startActivity(con);
    }

    public void calendar(View v) {
        Intent cal = new Intent(this, MainCalendarActivity.class);
        startActivity(cal);
    }

    public void camera(View v) {
        Intent cam = new Intent(this, CameraActivity.class);
        startActivity(cam);
    }


    public void getDashboardname(String key) {

        DatabaseReference ref = database.getReference("hairstylists/" + key);
        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("fname").getValue(String.class);

                TextView title = findViewById(R.id.textView);

                title.setText("WELCOME " + name);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

}