package com.example.mobileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class previousStyles extends AppCompatActivity {

    String[] styleDate = new String[]{
            "date1", "date2", "date3", "date4", "date5"
    };

    int[] pic = new int[]{
            R.drawable.profile, R.drawable.profile, R.drawable.profile, R.drawable.profile, R.drawable.profile
    };

    String[] description = new String[]{
            "des1", "des2", "des3", "des4", "des5"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_styles);

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i = 0; i < styleDate.length; i++){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("date", styleDate[i]);
            hm.put("pic", Integer.toString(pic[i]));
            hm.put("description", description[i]);
        }

        String[] from = {"styleDate", "pic", "description"};
        int[] to = {R.id.listview_image, R.id.item_title, R.id.item_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);



    }

}
