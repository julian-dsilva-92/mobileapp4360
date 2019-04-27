package com.com.mobileapp.dB;



import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobileapp.newappt.ApptSet;
import com.mobileapp.newappt.newAppt;

import java.util.HashMap;
import java.util.Map;


public class dBInitialize {
    public String keys = "";
    public static String custKey;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public void setAppointment(String day, String month, String year, int startTime, int endTime, String amPmstart, String amPmend, String customerID, String notes, String Stylistskey) {

        DatabaseReference appointmentRef = database.getReference("appointments");
        appointmentRef.push().setValue(new ApptSet(day, month, year, startTime, endTime, amPmstart, amPmend, customerID, notes, Stylistskey));
    }


    public void phoneexists() {
        getCustomerkey(new MyCallback() {
            @Override
            public void onCallback(String value) {
                custKey = value;

            }
        });

    }

    public boolean customerExists() {

        if (custKey != null) {
            return true;

        } else
            custKey = "";
        return false;
    }


    public void getCustomerName(String customerKey){
        DatabaseReference ref=database.getReference("customers");
        ref.child(customerKey).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    String module = map.get("firstName").toString();
                    String room = map.get("lastName").toString();
                    System.out.println(module + " " + room);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


}

    public void getCustomerkey(final MyCallback myCallback) {
        DatabaseReference ref=database.getReference("customers");
        ref.orderByChild("phone").equalTo(newAppt.setphone).addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    keys=datas.getKey();
                }
                myCallback.onCallback(keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }


    public interface MyCallback {
        void onCallback(String value);
    }



/*
    public void getAppointment() {

        DatabaseReference myRef = database.getReference("appointments/appointment1");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String test = dataSnapshot.child("customerID").getValue(String.class);
                System.out.println("Value is: " + test);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value." + error.toException());
            }
        });
    }
*/



    }


