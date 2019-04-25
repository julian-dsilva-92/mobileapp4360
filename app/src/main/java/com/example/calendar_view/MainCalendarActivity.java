package com.example.calendar_view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileapp.newappt.newAppt;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainCalendarActivity extends AppCompatActivity {

    public GregorianCalendar cal_month, cal_month_copy;
    private eventAdapter eventadapter;
    private TextView tv_month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApptDetails.apptArray=new ArrayList<ApptDetails>();
        ApptDetails.apptArray.add(new ApptDetails( "07",  "05",  "2019",  10,  11,  "am",  "am", "Timmy Turner",  "Wants a fade"));
        ApptDetails.apptArray.add(new ApptDetails( "07",  "05",  "2019",  11,  12,  "am",  "pm", "Timmy Johnson",  "Haircut"));
        ApptDetails.apptArray.add(new ApptDetails( "08",  "05",  "2019",  10,  11,  "am",  "pm", "Tim John",  "Haircut with dye"));



        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        eventadapter = new eventAdapter(this, cal_month,ApptDetails.apptArray);

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

        yourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainCalendarActivity.this, newAppt.class));
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


