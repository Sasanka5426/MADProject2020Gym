package com.example.madproject2020gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gymtime extends AppCompatActivity {

    private Button gymtimeAddDiet;
    private Button calBMI;
    private Button updateprobutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymtime);

        gymtimeAddDiet = (Button) findViewById(R.id.gymtimeadddiet);
        calBMI = (Button) findViewById(R.id.gymtimebmi);
        updateprobutton = (Button) findViewById(R.id.updateprobutton);

        gymtimeAddDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(gymtime.this,DietPlanCustomer.class);
                startActivity(intent);

            }
        });
        calBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  intent = new Intent(gymtime.this,bmicalculator.class);
                startActivity(intent);
            }
        });

        updateprobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gymtime.this,UpdateMyProfile.class);
                startActivity(intent);
            }
        });

    }
}