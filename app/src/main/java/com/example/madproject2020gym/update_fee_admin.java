package com.example.madproject2020gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class update_fee_admin extends AppCompatActivity {
    EditText txtDisplayFee;
    Button btnUpdateFee;
    Fee fee;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fee_admin);

        txtDisplayFee = findViewById(R.id.editTextDispFee);
        btnUpdateFee = findViewById(R.id.buttonUpdate);

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Fee").child("fee1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    txtDisplayFee.setText(snapshot.child("monthlyFee").getValue().toString());


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fee = new Fee();

        btnUpdateFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Fee");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("fee1")){
                            try {
                                fee.setFee1(Integer.parseInt(txtDisplayFee.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Fee").child("fee1");
                                dbRef.setValue(fee);

                                Toast.makeText(getApplicationContext(), "Fee Updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Sorry Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });
            }
        });

    }
}