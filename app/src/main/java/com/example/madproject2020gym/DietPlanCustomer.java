package com.example.madproject2020gym;

import android.os.Bundle;
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
public class DietPlanCustomer extends AppCompatActivity {
   private String memberloginphone = "";
    EditText txtEmail;
    EditText txtDiet, txtSolution, txtProcess, txtDescription;

    Button btnAddDiet, btnShowDiet, btnUpdateDiet, btnDeleteDiet;
    DatabaseReference dbRef;
    Dietplan dep;
    FirebaseDatabase rootNode;
    String dietEmail = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan_customer);

       memberloginphone = getIntent().getStringExtra("memberloginphone");

        txtEmail = findViewById(R.id.adddietemail);
        txtDiet = findViewById(R.id.adddietdiet);
        txtSolution = findViewById(R.id.dietsolution);
        txtProcess = findViewById(R.id.dietprocess);
        txtDescription = findViewById(R.id.dietdiscription);

        btnAddDiet = findViewById(R.id.dietadddiet);
        btnShowDiet = findViewById(R.id.adddietssow);
        btnUpdateDiet = findViewById(R.id.dietupdate);
        btnDeleteDiet = findViewById(R.id.dietdelete);

        dep = new Dietplan();



        btnAddDiet.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {



                dbRef = FirebaseDatabase.getInstance().getReference().child("Dietplan");
                try {
                    dep.setDietEmail(txtEmail.getText().toString().trim());
                    dep.setDiet(txtDiet.getText().toString().trim());
                    dep.setDietSolution(txtSolution.getText().toString().trim());
                    dep.setDietProcess(txtProcess.getText().toString().trim());
                    dep.setDietDescription(txtDescription.getText().toString().trim());

                    dbRef.push().setValue(dep);

                    dbRef.child(dep.getDietEmail()).setValue(dep);
                    //dbRef.setValue(dep);

                    Toast.makeText(DietPlanCustomer.this, "Successfull", Toast.LENGTH_SHORT).show();
                    clearControls();

                } catch (NumberFormatException e) {

                    Toast.makeText(DietPlanCustomer.this, "Un Successfull", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnShowDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Dietplan").child(memberloginphone);
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            txtEmail.setText(dataSnapshot.child("dietEmail").getValue().toString());
                            txtDiet.setText(dataSnapshot.child("diet").getValue().toString());
                            txtSolution.setText(dataSnapshot.child("dietSolution").getValue().toString());
                            txtProcess.setText(dataSnapshot.child("dietProcess").getValue().toString());
                            txtDescription.setText(dataSnapshot.child("dietDescription").getValue().toString());
                        }
                        else
                            Toast.makeText(DietPlanCustomer.this, "No", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnUpdateDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Dietplan");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(memberloginphone)){
                            try{
                                dep.setDietEmail(txtEmail.getText().toString().trim());
                                dep.setDiet(txtDiet.getText().toString().trim());
                                dep.setDietSolution(txtSolution.getText().toString().trim());
                                dep.setDietProcess(txtProcess.getText().toString().trim());
                                dep.setDietDescription(txtDescription.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Dietplan").child(memberloginphone);
                                dbRef.setValue(dep);
                                clearControls();

                                Toast.makeText(DietPlanCustomer.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btnDeleteDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("Dietplan");
                delref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(memberloginphone)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Dietplan").child(memberloginphone);
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(DietPlanCustomer.this, "Delete", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(DietPlanCustomer.this, "No", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }



    private void clearControls(){
        txtEmail.setText("");
        txtDiet.setText("");
        txtSolution.setText("");
        txtProcess.setText("");
        txtDescription.setText("");
    }
}