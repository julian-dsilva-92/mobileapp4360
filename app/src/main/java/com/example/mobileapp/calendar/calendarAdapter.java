package com.example.mobileapp.calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobileapp.R;

import java.util.ArrayList;

public class calendarAdapter extends BaseAdapter{

    Activity activity;

    private Activity context;
    private ArrayList<calendarDetails> alCustom;
    private String sturl;


    public calendarAdapter(Activity context, ArrayList<calendarDetails> alCustom) {
        this.context = context;
        this.alCustom = alCustom;

    }

    @Override
    public int getCount() {
        return alCustom.size();

    }

    @Override
    public Object getItem(int i) {
        return alCustom.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.O)


    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.row_addapt, null, true);

        TextView client=(TextView)listViewItem.findViewById(R.id.tv_name);
        TextView apptTime=(TextView)listViewItem.findViewById(R.id.tv_type);
        TextView notes=(TextView)listViewItem.findViewById(R.id.tv_class);


        client.setText("Name : "+alCustom.get(position).getClient());
        apptTime.setText("Appointment Time : "+alCustom.get(position).getApptTime());
        notes.setText("Notes: "+alCustom.get(position).getApptnotes());

        return  listViewItem;

    }

}
