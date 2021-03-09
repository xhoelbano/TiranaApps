package com.example.al.qwe.myapplicationosmtrial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView markertext;
    TextView location;
    TextView long_dscp;
    TextView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        markertext=findViewById(R.id.marker);
        //get data from main_activity
        String title = getIntent().getStringExtra("title");
        markertext.setText(title);

        location=findViewById(R.id.location);
        //get data from main_activity
        String location_dscp = getIntent().getStringExtra("loc");
        location.setText(location_dscp);

        long_dscp=findViewById(R.id.long_dscp);
        //get data from main_activity
        String long_description = getIntent().getStringExtra("long_dscp");
        long_dscp.setText(long_description);

        contact = findViewById(R.id.contact);
        String get_contact = getIntent().getStringExtra("contact");
        contact.setText(get_contact);
    }
}