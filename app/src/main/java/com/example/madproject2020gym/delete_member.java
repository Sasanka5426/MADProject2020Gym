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

public class delete_member extends AppCompatActivity {

    private String memberPhone = "";

    EditText txtName, txtAge, txtEmail, txtPhone, txtPackage, txtWeight;
    Button btnDelete;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_member);

        memberPhone = getIntent().getStringExtra("memberPhone");

        txtName = findViewById(R.id.editTextTextPersonName);
        txtAge = findViewById(R.id.editTextTextPersonAge);
        txtPhone = findViewById(R.id.editTextTextPersonPhone);
        txtEmail = findViewById(R.id.editTextTextPersonEmail);
        txtPackage = findViewById(R.id.editTextTextPersonPkg);
        txtWeight = findViewById(R.id.editTextTextPersonWeight);

        btnDelete = findViewById(R.id.buttonDelete2);

        txtName.setEnabled(false);
        txtAge.setEnabled(false);
        txtPhone.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPackage.setEnabled(false);
        txtWeight.setEnabled(false);


        getMemberDetails(memberPhone);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Member");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(memberPhone)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Member").child(memberPhone);
                            dbRef.removeValue();

                            Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(delete_member.this, manage_members_admin.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });








    }

    private void getMemberDetails(String memberPhone) {

        DatabaseReference memberRef = FirebaseDatabase.getInstance().getReference().child("Member");
        memberRef.child(memberPhone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Member member = dataSnapshot.getValue(Member.class);

                    txtName.setText(member.getName());
                    txtAge.setText(member.getAge());
                    txtEmail.setText(member.getEmail());
                    txtPhone.setText(member.getPhone());
                    txtPackage.setText(member.getPkg().toString());
                    txtWeight.setText(member.getWeight());




                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}