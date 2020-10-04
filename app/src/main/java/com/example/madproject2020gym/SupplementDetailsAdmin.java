package com.example.madproject2020gym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SupplementDetailsAdmin extends AppCompatActivity {

    private ImageView supplementImage;
    private TextView supplementName,supplementDescription,supplementPrice;
    private String supplementID = "", state= "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_details_admin);

        supplementID = getIntent().getStringExtra("supid");

        supplementImage = (ImageView) findViewById(R.id.supplement_image_details);
        supplementName = (TextView) findViewById(R.id.details_supplement_name);
        supplementDescription = (TextView) findViewById(R.id.details_supplement_description);
        supplementPrice = (TextView) findViewById(R.id.details_supplement_price);


    }
}