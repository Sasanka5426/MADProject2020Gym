package com.example.madproject2020gym;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class productView extends AppCompatActivity {

    ImageView imageView;
    TextView txtName;

    protected void onCreate(Bundle savedInterfaceState) {
        super.onCreate(savedInterfaceState);
        setContentView(R.layout.activity_product_view);
        imageView=findViewById(R.id.image);
        txtName=findViewById(R.id.txtName);

        Intent intent=getIntent();
        String image=intent.getStringExtra("IMAGE");

        Glide.with(this).load(image).into(imageView);

        String itemName=intent.getStringExtra("NAME");

        txtName.setText(itemName);


    }
}

