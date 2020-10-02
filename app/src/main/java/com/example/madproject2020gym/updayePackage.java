package com.example.madproject2020gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updayePackage extends AppCompatActivity {

    EditText txtpkgName, txtpkgDeduct, txtpkgDescription;
    Button btnUpdate, btnDelete;
    DatabaseReference dbRef;

    private String packageName;
    Packages pkg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updaye_package);

        packageName = getIntent().getStringExtra("packageName");

        txtpkgName = findViewById(R.id.editTextpkgName);
        txtpkgDeduct = findViewById(R.id.editTextDeduction);
        txtpkgDescription = findViewById(R.id.editTextDescription);

        txtpkgName.setEnabled(false);

        getPackageDetails(packageName);

        btnDelete = findViewById(R.id.buttonDelete);
        btnUpdate = findViewById(R.id.buttonUpdate);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Packages");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(packageName)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Packages").child(packageName);
                            dbRef.removeValue();

                            Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(updayePackage.this, packages_admin.class);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Packages");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(packageName)){
                            try{
                                pkg = new Packages();

                                pkg.setPkgName(txtpkgName.getText().toString().trim());
                                pkg.setDeduction(Integer.parseInt(txtpkgDeduct.getText().toString().trim()));
                               // pkg.setDescription(txtpkgDescription.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Packages").child(packageName);
                                dbRef.setValue(pkg);

                                Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();


                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(),"Failed to Update",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Packages");
//                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.hasChild()){
//                            dbRef = FirebaseDatabase.getInstance().getReference().child("Packages").child(txtpkgName.toString());
//                            dbRef.removeValue();
//
//                            Toast.makeText(getApplicationContext(),"Package deleted Successfully",Toast.LENGTH_SHORT).show();
//
//                            Intent intent = new Intent(updayePackage.this,packages_admin.class);
//                            startActivity(intent);
//
//
//                        }
//                        else
//                            Toast.makeText(getApplicationContext(),"Failed to delete :(",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });




    }

    private void getPackageDetails(String packageName) {

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Packages").child(packageName);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){


                    txtpkgName.setText(dataSnapshot.child("pkgName").getValue().toString());

                    txtpkgDeduct.setText(dataSnapshot.child("deduction").getValue().toString());

                    //txtpkgDescription.setText(dataSnapshot.child("description").getValue().toString());



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



//        DatabaseReference packageRef = FirebaseDatabase.getInstance().getReference().child("Packages");
//        packageRef.child(packageName).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
//                    Packages pkgs = dataSnapshot.getValue(Packages.class);
//                    txtpkgName.setText(pkgs.getPkgName());
//                    txtpkgDeduct.setText(pkgs.getDeduction());
//                    txtpkgDescription.setText(pkgs.getDescription());
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }
}