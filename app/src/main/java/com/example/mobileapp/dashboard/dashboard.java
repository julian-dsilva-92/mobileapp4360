package com.example.mobileapp.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mobileapp.R;
import com.example.mobileapp.calendar.MainCalendarActivity;
import com.example.mobileapp.camera.CameraActivity;
import com.example.mobileapp.dB.firebaseDashboard;

import java.util.List;

public class dashboard extends AppCompatActivity {

private RecyclerView mrecyclerview;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        mrecyclerview = (RecyclerView) findViewById(R.id.bookrecyclerview);
        new firebaseDashboard().getdashboarddata(new firebaseDashboard.getallAppts() {
            @Override
            public void Dataisloaded(List<getApptDashboard> appts) {
                new recylerviewconfig().setconfig(mrecyclerview,dashboard.this, appts);
            }
        });


    }
    public void ContactList (View v){
        Intent con = new Intent(this, ContactList.class);
        startActivity(con);
    }

    public void calendar (View v ){
        Intent cal = new Intent(this, MainCalendarActivity.class);
        startActivity(cal);
    }

    public void camera (View v) {
        Intent cam = new Intent(this, CameraActivity.class);
        startActivity(cam);
    }
}