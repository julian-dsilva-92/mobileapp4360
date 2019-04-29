package com.example.mobileapp.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mobileapp.R;
import com.example.mobileapp.calendar.MainCalendarActivity;
import com.example.mobileapp.camera.CameraActivity;

public class dashboard extends AppCompatActivity {


    /*private ImageButton calendar;
    private ImageButton camera;
*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

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