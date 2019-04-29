package com.example.mobileapp.dashboard;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobileapp.R;


public class contact_row extends ArrayAdapter {


    private final Activity context;


    private final String[] nameArray;


    private final String[] phoneArray;

    public contact_row (Activity context, String[] nameArrayParam, String[] phoneArrayParam) {

        super(context, R.layout.contact_row_view, nameArrayParam);
        this.context = context;
        this.nameArray = nameArrayParam;
        this.phoneArray = phoneArrayParam;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.contact_row_view, null,true);

        TextView nameTextField =  rowView.findViewById(R.id.row_nameid);
        TextView phoneTextField =  rowView.findViewById(R.id.row_phoneid);

        nameTextField.setText(nameArray[position]);
        phoneTextField.setText(phoneArray[position]);

        return rowView;

    }
}
