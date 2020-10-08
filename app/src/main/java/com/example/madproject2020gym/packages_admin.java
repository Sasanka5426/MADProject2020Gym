 package com.example.madproject2020gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import viewHolderSk.PackageViewHolderSK;

 public class packages_admin extends AppCompatActivity {

     private DatabaseReference pkgRef;
     private RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutManager;
     Button btnAddNewPkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_admin);

        pkgRef = FirebaseDatabase.getInstance().getReference().child("Packages");

        recyclerView = findViewById(R.id.packages_admin);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        btnAddNewPkg = findViewById(R.id.buttonAddNewPackage);

        btnAddNewPkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(packages_admin.this,add_new_package_admin.class);
                startActivity(intent);
            }
        });






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
                    protected void onBindViewHolder(@NonNull PackageViewHolderSK packageViewHolderSK, int i, @NonNull final Packages2SK packages2SK)
                    {
                        packageViewHolderSK.txtPackageName.setText("Package : " + packages2SK.getPkgName());
                        packageViewHolderSK.txtPackageDescription.setText(packages2SK.getDescription());

                        packageViewHolderSK.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(packages_admin.this, updayePackage.class);
                                intent.putExtra("packageName", packages2SK.getPkgName());
                                startActivity(intent);
                            }
                        });
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