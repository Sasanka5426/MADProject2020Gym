package com.example.madproject2020gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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

public class add_new_member_admin extends AppCompatActivity {
    EditText txtName, txtAge, txtWeight, txtEmail, txtPackage, txtPassword;
    Button btnAdd;
    int monthlyFeeFinal, deductFinal;
    DatabaseReference dbRef;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_member_admin);

        txtName = findViewById(R.id.editTextName);
        txtAge = findViewById(R.id.editTextAge);
        txtWeight = findViewById(R.id.editTextWeight);
        txtEmail = findViewById(R.id.editTextEmail);
        txtPackage = findViewById(R.id.editTextPackage);
        txtPassword = findViewById(R.id.editTextPassword);
        


        btnAdd =  findViewById(R.id.buttonAdd);

        member = new Member();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtAge.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Age", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtPackage.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Package", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtWeight.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Initial Weight", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtPassword.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_SHORT).show();


                    else {

                        try {
                            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Packages").child(txtPackage.toString());
                            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChildren()) {
                                        String deductStr = snapshot.child("deduction").getValue().toString();
                                        int deductInt = Integer.parseInt(deductStr);
                                        deductFinal = deductInt;
                                    } else
                                        Toast.makeText(getApplicationContext(), "Error: Please check the Package name", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        catch(Exception ex){
                            Toast.makeText(getApplicationContext(), "Error occurred in deduction ", Toast.LENGTH_SHORT).show();
                        }

                        try {
                            DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("Fee").child("fee1");
                            readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChildren()) {
                                        String monthlyFeeStr = snapshot.child("fee1").getValue().toString();
                                        int monthlyFeeInt = Integer.parseInt(monthlyFeeStr);
                                        monthlyFeeFinal = monthlyFeeInt;
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error: Could not retrieve the monthly fee", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                        catch(Exception ex){
                            Toast.makeText(getApplicationContext(), "mnsdcvdfbsssssssssssssssssssssssssssssssssssssssssssss", Toast.LENGTH_SHORT).show();

                        }

                        try {
                            String packageStr = txtPackage.toString();
                            int packageInt = Integer.parseInt(packageStr);


                            Double finalFee = (monthlyFeeFinal * packageInt) * (100 - deductFinal) / 100.0;


                            dbRef = FirebaseDatabase.getInstance().getReference().child("Member");

                            member.setName(txtName.getText().toString().trim());
                            member.setAge(txtAge.getText().toString().trim());
                            member.setPkg(Integer.parseInt(txtPackage.getText().toString().trim()));
                            member.setWeight(txtWeight.getText().toString().trim());
                            member.setFee(finalFee.toString().trim());
                            member.setPassword(txtPassword.getText().toString().trim());
                            member.setEmail(txtEmail.getText().toString().trim());

                            //insert int database
                            dbRef.child(member.getEmail()).setValue(member);
                            //feedback to the user via a toast

                            Toast.makeText(getApplicationContext(), "Member data saved Successfully", Toast.LENGTH_SHORT).show();
                        }
                        catch(Exception ex){
                            Toast.makeText(getApplicationContext(), "Error yakooooo", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                catch(Exception ex){
                    Toast.makeText(getApplicationContext(), "Sorry, something went wrong :(", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}