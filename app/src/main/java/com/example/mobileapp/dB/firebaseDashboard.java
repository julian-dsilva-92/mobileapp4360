package com.example.mobileapp.dB;

import android.support.annotation.NonNull;

import com.example.mobileapp.dashboard.getApptDashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebaseDashboard
{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("appointments");
                private List<getApptDashboard> appts = new ArrayList<>();

                public void getdashboarddata(final getallAppts allappts){


                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                getApptDashboard appt = data.getValue(getApptDashboard.class);
                                appts.add(appt);

                            }
                            allappts.Dataisloaded(appts);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    public interface getallAppts {
         void Dataisloaded(List<getApptDashboard> appts);
    }


}

