package com.example.madproject2020gym;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Member.Member;


public class CustomerLogin extends AppCompatActivity {

    private EditText inputphone ;
    private EditText inputpassword;
    private Button login;
    private String parentDbName = "Member";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        inputphone = (EditText) findViewById(R.id.logphone);
        inputpassword = (EditText) findViewById(R.id.loginpassword);
        login = (Button) findViewById(R.id.loginbutton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginuser();
            }
        });
    }
    private void loginuser(){

        String phone = inputphone.getText().toString();
        String password  = inputpassword.getText().toString();

        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Enter your Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
        }
        else {
            AllowAccessToAcount(phone ,password);
        }
    }
    private void AllowAccessToAcount(final String phone , final String password )
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(parentDbName).child(phone).exists()){

                    Member userdata = snapshot.child(parentDbName).child(phone).getValue(Member.class);
                    if(userdata.getPhone().equals(phone)){

                        if(userdata.getPassword().equals(password)){
                            Toast.makeText(CustomerLogin.this, "Logging successFully", Toast.LENGTH_SHORT).show();

                           Intent intent = new Intent(CustomerLogin.this,Wellcomepage.class);
                           intent.putExtra("memberloginphone",phone);
                            startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(CustomerLogin.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(CustomerLogin.this, "Account with this "+phone+" Number do not exits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}