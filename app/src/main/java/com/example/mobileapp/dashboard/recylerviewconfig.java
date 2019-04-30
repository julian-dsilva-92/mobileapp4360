package com.example.mobileapp.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileapp.R;

import java.util.List;

public class recylerviewconfig {
    private Context mcontext;

    private dashboardappointview dashboardviewAdapter;

    public void setconfig(RecyclerView recyleview, Context context , List<getApptDashboard> appts){

        mcontext = context;
        dashboardviewAdapter = new dashboardappointview(appts);
        recyleview.setLayoutManager(new LinearLayoutManager(context));
        recyleview.setAdapter(dashboardviewAdapter);

    }


    class appointmentview extends RecyclerView.ViewHolder {

        private TextView apptnotes;
        private TextView starttime;
        private TextView endtime;
        private TextView customerID;
        private TextView amPmstart;
        private TextView amPmend;


        public appointmentview(ViewGroup parent) {


            super(LayoutInflater.from(mcontext).inflate(R.layout.appointment_list_iteam, parent, false));

            apptnotes = itemView.findViewById(R.id.notesView);
            starttime = itemView.findViewById(R.id.startTimeview);
            endtime = itemView.findViewById(R.id.endTimeview);
            customerID = itemView.findViewById(R.id.textView6);
            amPmstart = itemView.findViewById(R.id.startamPmview);
            amPmend = itemView.findViewById(R.id.textView10);

        }


        public void bind(getApptDashboard data) {
            apptnotes.setText(data.getNotes());
            starttime.setText(data.getStartTime());
            endtime.setText(data.getEndTime());
            customerID.setText(data.getCustomerID());
            amPmstart.setText(data.getAmPmstart());
            amPmend.setText(data.getAmPmend());


        }
    }

        class dashboardappointview extends RecyclerView.Adapter<appointmentview>{

            private List<getApptDashboard> mappointments;

            public dashboardappointview(List<getApptDashboard> mappointments) {
                this.mappointments = mappointments;
            }

            @NonNull
            @Override
            public appointmentview onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                return new appointmentview(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull appointmentview appointmentview, int i) {
                appointmentview.bind(mappointments.get(i));

            }

            @Override
            public int getItemCount() {
                return mappointments.size();
            }
        }

    }


