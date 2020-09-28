 package com.example.madproject2020gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import viewHolderSk.PackageViewHolderSK;

 public class packages_admin extends AppCompatActivity {

     private DatabaseReference pkgRef;
     private RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_admin);

        pkgRef = FirebaseDatabase.getInstance().getReference().child("Packages");

        recyclerView = findViewById(R.id.packages_admin);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //pkgRef = FirebaseDatabase.getInstance().getReference().child("Packages");

        FirebaseRecyclerOptions<Packages2SK> options = new FirebaseRecyclerOptions.Builder<Packages2SK>()
                .setQuery(pkgRef, Packages2SK.class).build();

        FirebaseRecyclerAdapter<Packages2SK, PackageViewHolderSK> adapter =
                new FirebaseRecyclerAdapter<Packages2SK, PackageViewHolderSK>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull PackageViewHolderSK packageViewHolderSK, int i, @NonNull Packages2SK packages2SK)
                    {
                        packageViewHolderSK.txtPackageName.setText("Package : " + packages2SK.getPkgName());
                        packageViewHolderSK.txtPackageDescription.setText(packages2SK.getDescription());
                    }

                    @NonNull
                    @Override
                    public PackageViewHolderSK onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.packages_list_layout, parent, false);
                        PackageViewHolderSK holder = new PackageViewHolderSK(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}