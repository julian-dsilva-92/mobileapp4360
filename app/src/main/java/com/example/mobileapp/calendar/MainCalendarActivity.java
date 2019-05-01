package com.example.mobileapp.calendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.dashboard.dashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class MainCalendarActivity extends AppCompatActivity {

    public GregorianCalendar cal_month, cal_month_copy;
    private eventAdapter eventadapter;
    private TextView tv_month;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private List<getApptDetails> appointmentDetail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        getSupportActionBar().hide();

        ApptDetails.apptArray = new ArrayList<ApptDetails>();

        DatabaseReference ref = database.getReference("appointments");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appointmentDetail.clear();
                for (DataSnapshot apptDetails : dataSnapshot.getChildren()) {
                    getApptDetails newAppt = new getApptDetails();
                    newAppt.setDay(apptDetails.child("day").getValue().toString());
                    newAppt.setMonth(apptDetails.child("month").getValue().toString());
                    newAppt.setYear(apptDetails.child("year").getValue().toString());
                    newAppt.setStartTime(apptDetails.child("startTime").getValue().toString());
                    newAppt.setEndTime(apptDetails.child("endTime").getValue().toString());
                    newAppt.setAmPmstart(apptDetails.child("amPmstart").getValue().toString());
                    newAppt.setAmPmend(apptDetails.child("amPmend").getValue().toString());
                    newAppt.setNotes(apptDetails.child("notes").getValue().toString());
                    newAppt.setCustomerID(apptDetails.child("customerID").getValue().toString());


                    // adds appointments to the calendar view
                    ApptDetails.apptArray.add(new ApptDetails(newAppt.getDay(), newAppt.getMonth(), newAppt.getYear(), newAppt.getStartTime(), newAppt.getEndTime(), newAppt.getAmPmstart(), newAppt.getAmPmend(), newAppt.getCustomerID(), newAppt.getNotes()));

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        eventadapter = new eventAdapter(this, cal_month, ApptDetails.apptArray);

        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));


        ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPreviousMonth();
                refreshCalendar();

            }
        });
        ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();
            }
        });
        GridView gridview = (GridView) findViewById(R.id.gv_calendar);
        gridview.setAdapter(eventadapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedGridDate = eventAdapter.day_string.get(position);
                ((eventAdapter) parent.getAdapter()).getPositionList(selectedGridDate, MainCalendarActivity.this);
            }

        });

        Button yourButton = (Button) findViewById(R.id.apptButton);

        yourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), newAppt.class));
            }
        });

        Button Button2 = (Button) findViewById(R.id.apptButton2);

        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
            }
        });


    }

    protected void setNextMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH,
                    cal_month.get(GregorianCalendar.MONTH) + 1);
        }
    }

    protected void setPreviousMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMinimum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) - 1);
        }
    }

    public void refreshCalendar() {
        eventadapter.refreshDays();
        eventadapter.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
    }
}


