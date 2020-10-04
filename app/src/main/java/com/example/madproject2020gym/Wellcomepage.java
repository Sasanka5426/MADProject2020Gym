package com.example.madproject2020gym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Wellcomepage extends AppCompatActivity {

    private Button gymbtn , logoutButton;
    private String phonec = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcomepage);

        phonec = getIntent().getStringExtra("memberloginphone");

        gymbtn = (Button) findViewById(R.id.welcomegymb);
        logoutButton = (Button) findViewById(R.id.logOutButton) ;

        gymbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Wellcomepage.this,gymtime.class);
                intent.putExtra("memberloginphone",phonec);
                startActivity(intent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Wellcomepage.this,CustomerLogin.class);

                startActivity(intent);
            }
        });

    }
}