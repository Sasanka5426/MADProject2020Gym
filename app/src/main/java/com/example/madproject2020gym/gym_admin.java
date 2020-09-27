package com.example.madproject2020gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gym_admin extends AppCompatActivity {

    Button btnAddMember, btnManageMember, btnPackages, btnUpdateFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_admin);

        btnAddMember = findViewById(R.id.buttonAddNewMember);
        btnManageMember = findViewById(R.id.buttonManageMember);
        btnPackages = findViewById(R.id.buttonPackages);
        btnUpdateFee = findViewById(R.id.buttonUpdateFee);

        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(gym_admin.this,add_new_member_admin.class);
                startActivity(intent1);

            }
        });

        /*btnManageMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(gym_admin.this,manage_members_admin.class);
                startActivity(intent2);
            }
        });*/

        /*btnPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(gym_admin.this,packages_admin.class);
                startActivity(intent3);
            }
        });*/

        btnUpdateFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(gym_admin.this,update_fee_admin.class);
                startActivity(intent4);
            }
        });

    }
}