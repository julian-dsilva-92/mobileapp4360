package com.example.mobileapp.dashboard;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.mobileapp.ProfileActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.loginregistration.NewClient;
import com.example.mobileapp.loginregistration.NewUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ContactList extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();


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

        DatabaseReference ref = database.getReference("customers");

        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



            final EditText search = findViewById(R.id.editText4);
        search.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                DatabaseReference ref = database.getReference("customers");

                ref.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> list = new ArrayList<String>();
                        if (dataSnapshot.exists())
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {

                                list.add(datas.getKey());

                                getNameandPhone(list);
                            }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


            public void returnMenu (View view){
        Intent menu = new Intent(this, dashboard.class);
        startActivity(menu);
 }

    public void addContact(View view){
            Intent add = new Intent(this, NewClient.class);
            startActivity(add);
        }






        public void getNameandPhone (final ArrayList<String> list) {
            final String [] nArray = new String[list.size()];
            final String [] pArray = new String[list.size()];

            for (int i = 0; i < list.size(); i++) {

                DatabaseReference ref = database.getReference("customers/" + list.get(i));
                final int finalI = i;
                ref.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                      String fname = dataSnapshot.child("firstName").getValue(String.class);
                      String  lname = dataSnapshot.child("lastName").getValue(String.class);
                      String phone = dataSnapshot.child("phone").getValue(String.class);
                      String name = fname + " " +  lname;
                      nArray[finalI] = name;
                      pArray[finalI] = phone;
                        contact_row custom = new contact_row(ContactList.this, nArray, pArray);///rows of list view
                        listView = findViewById(R.id.contact_list);
                        listView.setAdapter(custom);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String key = list.get(position);
                                Intent intent = new Intent(ContactList.this, ProfileActivity.class);
                                intent.putExtra("keyValue", key);
                                startActivity(intent);
                            }
                        });




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }

            }







