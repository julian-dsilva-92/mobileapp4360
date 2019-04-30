package com.example.mobileapp.dB;

import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.calendar.ApptSet;
import com.example.mobileapp.calendar.newAppt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class dBInitialize {


    public String keys = "";

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    //sets the appointment
    public void setAppointment(String day, String month, String year, String startTime, String endTime, String amPmstart, String amPmend, String customerID, String notes, String Stylistskey, String phone) {

        DatabaseReference appointmentRef = database.getReference("appointments");
        appointmentRef.push().setValue(new ApptSet(day, month, year, startTime, endTime, amPmstart, amPmend, customerID, notes, Stylistskey, phone));
    }


    //returns customer key to attach to a new appointment and name validation
    public void getCustomerkey(final KeyCallback myCallback) {
        DatabaseReference ref = database.getReference("customers");
        ref.orderByChild("phone").equalTo(newAppt.setphone).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        keys = datas.getKey();
                    }


                myCallback.onKeyCallback(keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



    public interface KeyCallback {
        void onKeyCallback(String value);

    }



}





