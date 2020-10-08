package com.example.madproject2020gym;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_new_package_admin extends AppCompatActivity {
    EditText txtName, txtDeduct, txtDescription;
    Button btnSave;
    DatabaseReference dbRef;
    Packages pkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_package_admin);

        txtName = findViewById(R.id.editTextPackageName);
        txtDeduct = findViewById(R.id.editTextDeduction);
        txtDescription = findViewById(R.id.editTextDescription);



        btnSave = findViewById(R.id.buttonSave);

        pkg = new Packages();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Packages");

                try{
                    if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter package name", Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(txtDeduct.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter deduction", Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(txtDescription.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a description", Toast.LENGTH_SHORT).show();

                    else{
                        pkg.setPkgName(txtName.getText().toString().trim());
                        pkg.setDeduction(Integer.parseInt(txtDeduct.getText().toString().trim()));
                        pkg.setDescription(txtDescription.getText().toString().trim());

                        //insert int database
                        dbRef.child(pkg.getPkgName()).setValue(pkg);
                        //feedback to the user via a toast

                        Toast.makeText(getApplicationContext(),"Package data saved Succcessfully", Toast.LENGTH_SHORT).show();


                    }
                }
                catch(Exception ex){
                    Toast.makeText(getApplicationContext(), "Proccess cannot be completed" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}