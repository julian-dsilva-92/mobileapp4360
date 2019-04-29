package com.com.mobileapp.dB;



import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.com.mobileapp.calendar.ApptSet;
import com.com.mobileapp.calendar.newAppt;
import com.example.mobileapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class dBInitialize {


    public String keys = "";

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    //sets the appointment
    public void setAppointment(String day, String month, String year, String startTime, String endTime, String amPmstart, String amPmend, String customerID, String notes, String Stylistskey, String phone) {

        DatabaseReference appointmentRef = database.getReference("appointments");
        appointmentRef.push().setValue(new ApptSet(day, month, year, startTime, endTime, amPmstart, amPmend, customerID, notes, Stylistskey, phone));
    }


    //returns customer key to attach to a new appointment and name validation
        public void getCustomerkey ( final KeyCallback myCallback){
            DatabaseReference ref = database.getReference("customers");
            ref.orderByChild("phone").equalTo(newAppt.setphone).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                        for (DataSnapshot datas : dataSnapshot.getChildren()) {
                            keys = datas.getKey();
                        }


                    myCallback.onKeyCallback(keys);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }


        public interface KeyCallback {
            void onKeyCallback(String value);

        }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static class CameraActivity extends AppCompatActivity {

        private static final int MULTIPLE_PERMISSIONS = 10; // code you want.
        private static final int REQUEST_IMAGE_CAPTURE = 1;

        private DatabaseReference myRef;
        private String currentPhotoPath;

        private Button btnUploadData;
        private Button btnCamera;
        private EditText etImageInfo;

        private ImageView imgView;

        private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        private String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_camera);

            getSupportActionBar().setTitle("Add a New Style");

            if (checkPermissions()) {
                //  permissions  granted.
            }

            btnUploadData = findViewById(R.id.btnUploadData);
            btnCamera = findViewById(R.id.btnCamera);
            etImageInfo = findViewById(R.id.etImageInfo);
            imgView = findViewById(R.id.imgView);

            myRef = FirebaseDatabase.getInstance().getReference("hairstyles");

            btnUploadData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);

                    String base64Img = imgEncode(bitmap);

                    String id = myRef.push().getKey();

                    LocalDate localDate = LocalDate.now();
                    String curDate = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(localDate);

                    Record record = new Record(id, base64Img, etImageInfo.getText().toString(), curDate);
                    myRef.child(id).setValue(record);

                    startActivity(new Intent(getApplicationContext(), ImagesActivity.class));
                }
            });


            btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CameraActivity.this, "Take Picture Tapped", Toast.LENGTH_SHORT).show();
                    dispatchTakePictureIntent();
                }
            });
        }


        private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.firebaseintegration.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE); // changed to image capture

                }
            }
        }

        /**
         * returns unique file name and sotre it
         *
         * @return fileName
         * @throws IOException
         */
        private File createImageFile() throws IOException {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = image.getAbsolutePath();
            return image;
        }

        private void galleryAddPic() {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(currentPhotoPath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

                galleryAddPic();

                setPic();
            }
        }


        private boolean checkPermissions() {
            int result;
            List<String> listPermissionsNeeded = new ArrayList<>();
            for (String p : permissions) {
                result = ContextCompat.checkSelfPermission(CameraActivity.this, p);
                if (result != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(p);
                }
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
                return false;
            }
            return true;
        }

        private void setPic() {
            // Get the dimensions of the View
            int targetW = imgView.getWidth();
            int targetH = imgView.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
            imgView.setImageBitmap(bitmap);
        }

        public String imgEncode(final Bitmap image) {
            String encodedImage = "";

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] b = baos.toByteArray();

            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            return encodedImage;
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.mymenu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        // handle button activities
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.mybutton) {
                startActivity(new Intent(getApplicationContext(), ImagesActivity.class));
                // do something here
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public static class ImagesActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

        private List<Record> recordList;
        private RecyclerView recyclerView;
        private MyRecyclerViewAdapter adapter;

        private DatabaseReference myRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_images);

            getSupportActionBar().setTitle("My HairStyles");

            recordList = new ArrayList<>();
            recyclerView = findViewById(R.id.rvRecords);
            adapter = new MyRecyclerViewAdapter(getApplicationContext(), recordList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);


            myRef = FirebaseDatabase.getInstance().getReference("names");

            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public synchronized void onDataChange(DataSnapshot dataSnapshot) {
                    recordList.clear();
                    // Result will be holded Here
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        Record record = dsp.getValue(Record.class);
                        recordList.add(record);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("ERROR", "Failed to read value.", error.toException());
                }
            });
        }

        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        }

    }

    public static class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private List<Record> mData;
        private LayoutInflater mInflater;
        private ItemClickListener mClickListener;

        // data is passed into the constructor
        public MyRecyclerViewAdapter(Context context, List<Record> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.recycle_row, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String hairStyleInfo = mData.get(position).getHairStyleInfo();
            String hairStyleDate = mData.get(position).getHairStyleDate();
            String encodedImg = mData.get(position).getEncodedImage();

            holder.tvHairStyle.setText(hairStyleInfo);
            holder.tvDate.setText(hairStyleDate);
            holder.imgRecycler.setImageBitmap(imgDecode(encodedImg));
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }

        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tvHairStyle;
            TextView tvDate;
            ImageView imgRecycler;

            ViewHolder(View itemView) {
                super(itemView);
                tvHairStyle = itemView.findViewById(R.id.tvHairStyle);
                tvDate = itemView.findViewById(R.id.tvDate);
                imgRecycler = itemView.findViewById(R.id.imgRecycler);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        // convenience method for getting data at click position
        String getItem(int id) {
            return mData.get(id).toString();
        }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }

        public Bitmap imgDecode(final String image) {
            String encodedImage = "";

            byte[] decodedBytes = Base64.decode(image, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            return decodedBitmap;
        }
    }

    public static class Record {

        private String id;
        private String encodedImage;
        private String hairStyleInfo;
        private String hairStyleDate;

        public Record() {
        }

        public Record(String id, String encodedImage, String hairStyleInfo, String hairStyleDate) {
            this.id = id;
            this.encodedImage = encodedImage;
            this.hairStyleInfo = hairStyleInfo;
            this.hairStyleDate = hairStyleDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEncodedImage() {
            return encodedImage;
        }

        public void setEncodedImage(String encodedImage) {
            this.encodedImage = encodedImage;
        }

        public String getHairStyleInfo() {
            return hairStyleInfo;
        }

        public void setHairStyleInfo(String hairStyleInfo) {
            this.hairStyleInfo = hairStyleInfo;
        }

        public String getHairStyleDate() {
            return hairStyleDate;
        }

        public void setHairStyleDate(String hairStyleDate) {
            this.hairStyleDate = hairStyleDate;
        }
    }
}


/*
    public void getAppointment() {

        DatabaseReference myRef = database.getReference("appointments/appointment1");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String test = dataSnapshot.child("customerID").getValue(String.class);
                System.out.println("Value is: " + test);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value." + error.toException());
            }
        });
    }
*/






