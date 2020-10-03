package com.example.madproject2020gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Wellcomepage extends AppCompatActivity {

    private Button gymbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcomepage);

        gymbtn = (Button) findViewById(R.id.welcomegymb);

        gymbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Wellcomepage.this,gymtime.class);
                startActivity(intent);
            }
        });


    }
}