package com.example.madproject2020gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.madproject2020gym.Adapter.DataAdapter;
import com.example.madproject2020gym.Model.Supplements;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class store extends AppCompatActivity implements Callback {

    RecyclerView recyclerView;
    ArrayList<Supplements> arrayList;
    DataAdapter adapter;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        arrayList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Items Loading...");
        databaseReference = FirebaseDatabase.getInstance().getReference("Supplements");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Supplements supplements=ds.getValue(Supplements.class);
                    arrayList.add(supplements);

                }
                adapter=new DataAdapter(store.this,arrayList, store.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(store.this, "Error"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void onClick(int i){
        Intent intent=new Intent(store.this,productView.class);
        intent.putExtra("IMAGE",arrayList.get(i).getImage());
        intent.putExtra("NAME",arrayList.get(i).getSupname());
        startActivity(intent);
    }
}