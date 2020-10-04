package com.example.madproject2020gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainActivity extends AppCompatActivity {


    private Button applyChangeBtn, deleteBtn;
    private EditText name, price, description;
    private ImageView imageView;
    private String supplementID = "";

    private DatabaseReference supplymentRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain);


        supplementID = getIntent().getStringExtra("Supid");
        supplymentRef = FirebaseDatabase.getInstance().getReference().child(String.valueOf(supplementID));


        applyChangeBtn = findViewById(R.id.change_btn);
        name = findViewById(R.id.supplement_name_admin);
        price = findViewById(R.id.supplement_price_admin);
        description = findViewById(R.id.description_admin);
        imageView = findViewById(R.id.supplement_image_maintain);
        deleteBtn = findViewById(R.id.delete_btn);

        dispalyInfo();

        applyChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSupplemen();
            }
        });


        /*private void applyChanges ()
        {
            String sName = name.getText().toString();
            String sPrice = price.getText().toString();
            String sDescription = description.getText().toString();

            if (sName.equals("")) {
                Toast.makeText(this, "Write down Supplement Name", Toast.LENGTH_SHORT).show();
            } else if (sPrice.equals("")) {
                Toast.makeText(this, "Write down Supplement Price", Toast.LENGTH_SHORT).show();

            } else if (sDescription.equals("")) {
                Toast.makeText(this, "Write down Supplement Description", Toast.LENGTH_SHORT).show();

            } else {
                HashMap<String, Object> supplementMap = new HashMap<>();
                supplementMap.put("supid", supplementID);

                supplementMap.put("description", sDescription);
                supplementMap.put("price", sPrice);
                supplementMap.put("supname", sName);

                supplymentRef.updateChildren(supplementMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminMaintainActivity.this, "Changes applied successfully ", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AdminMaintainActivity.this, AdminCategory.class);
                            startActivity(intent);
                        }
                    }
                });

            }
        }*/




        /*private void deleteSupplemen ()
        {
            supplymentRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AdminMaintainActivity.this, "Changes applied successfully ", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AdminMaintainActivity.this, AdminCategory.class);
                    startActivity(intent);

                    Toast.makeText(AdminMaintainActivity.this, "The Supplement is deleted successfully", Toast.LENGTH_SHORT).show();

                }
            });
        }*/

    }

    private void deleteSupplemen()
    {
        supplymentRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AdminMaintainActivity.this, "Changes applied successfully ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AdminMaintainActivity.this, AdminCategory.class);
                startActivity(intent);

                Toast.makeText(AdminMaintainActivity.this, "The Supplement is deleted successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void applyChanges()
    {
        String sName = name.getText().toString();
        String sPrice = price.getText().toString();
        String sDescription = description.getText().toString();

        if (sName.equals("")) {
            Toast.makeText(this, "Write down Supplement Name", Toast.LENGTH_SHORT).show();
        } else if (sPrice.equals("")) {
            Toast.makeText(this, "Write down Supplement Price", Toast.LENGTH_SHORT).show();

        } else if (sDescription.equals("")) {
            Toast.makeText(this, "Write down Supplement Description", Toast.LENGTH_SHORT).show();

        } else {
            HashMap<String, Object> supplementMap = new HashMap<>();
            supplementMap.put("supid", supplementID);

            supplementMap.put("description", sDescription);
            supplementMap.put("price", sPrice);
            supplementMap.put("supname", sName);

            supplymentRef.updateChildren(supplementMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AdminMaintainActivity.this, "Changes applied successfully ", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AdminMaintainActivity.this, AdminCategory.class);
                        startActivity(intent);
                    }
                }
            });

        }
    }

    private void dispalyInfo()
    {
        supplymentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    String sName = dataSnapshot.child("supname").getValue().toString();
                    String sPrice = dataSnapshot.child("price").getValue().toString();
                    String sDescription = dataSnapshot.child("description").getValue().toString();
                    String sImage = dataSnapshot.child("image").getValue().toString();

                    name.setText((sName));
                    price.setText(sPrice);
                    description.setText(sDescription);
                    Picasso.get().load(sImage).into(imageView);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}