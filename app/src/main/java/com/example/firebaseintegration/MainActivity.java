package com.example.firebaseintegration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    private DatabaseReference myRef;

    private EditText etFirstName;
    private EditText etLastName;
    private Button btnUploadData;
    private List<Record> recordList;
    private RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordList = new ArrayList<>();
        recyclerView = findViewById(R.id.rvRecords);
        adapter = new MyRecyclerViewAdapter(getApplicationContext(), recordList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        btnUploadData = findViewById(R.id.btnUploadData);

        myRef = FirebaseDatabase.getInstance().getReference("names");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recordList.clear();
                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Record record = dsp.getValue(Record.class);
                    recordList.add(record);
                    adapter.notifyDataSetChanged();
                }

//                Toast.makeText(getApplicationContext(), "updated: " + recordList.toString(), Toast.LENGTH_SHORT).show();
                // set up the RecyclerView
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException());
            }
        });


        btnUploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUploadDataAction(etFirstName.getText().toString(), etLastName.getText().toString());
            }
        });
    }

    private void btnUploadDataAction(String a, String b) {
        String id = myRef.push().getKey();
        Record record = new Record(id, a, b);
        myRef.child(id).setValue(record);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
