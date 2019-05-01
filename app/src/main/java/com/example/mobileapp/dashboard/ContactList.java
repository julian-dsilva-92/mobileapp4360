package com.example.mobileapp.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mobileapp.R;
import com.example.mobileapp.loginregistration.NewClient;
import com.example.mobileapp.loginregistration.NewUser;


public class ContactList extends AppCompatActivity {


    String[] nameArray = {"Allie", "Bob", "Eric", "James", "Tom", "Zeus"};

    String[] phoneArray = {
            "678-233-1234",
            "697-123-6455",
            "546-145-7532",
            "563-425-5295",
            "923-546-4653",
            "359-323-1434"
    };
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);

        getSupportActionBar().hide();

        contact_row custom = new contact_row(this, nameArray, phoneArray);///rows of list view
        listView = findViewById(R.id.contact_list);
        listView.setAdapter(custom);

      //  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         //   @Override
          //  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   Intent intent = new Intent(view.getContext(), edit_contact.class);
              //  startActivity(intent);
     //       }
      //  });




    }


 public void returnMenu (View view){
        Intent menu = new Intent(this, dashboard.class);
        startActivity(menu);
 }

    public void addContact(View view){
            Intent add = new Intent(this, NewClient.class);
            startActivity(add);
        }

}


