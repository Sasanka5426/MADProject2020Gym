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

import viewHolderSk.memberViewHolder;

public class manage_members_admin extends AppCompatActivity {

    private DatabaseReference memberRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_members_admin);

        memberRef = FirebaseDatabase.getInstance().getReference().child("Member");
        recyclerView = findViewById(R.id.manage_members_admin);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Member2SK> options
                = new FirebaseRecyclerOptions.Builder<Member2SK>()
                .setQuery(memberRef, Member2SK.class)
                .build();

       FirebaseRecyclerAdapter<Member2SK, memberViewHolder> adapter =
               new FirebaseRecyclerAdapter<Member2SK, memberViewHolder>(options) {
                   @Override
                   protected void onBindViewHolder(@NonNull memberViewHolder memberViewHolder, int i, @NonNull Member2SK member2SK) {
                       memberViewHolder.txtMemberName.setText(member2SK.getName());
                       memberViewHolder.txtMemberEmail.setText("Email : "+member2SK.getEmail());
                       memberViewHolder.txtMemberPhone.setText("Phone : "+member2SK.getPhone());

                   }

                   @NonNull
                   @Override
                   public memberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_members_list_layout,parent,false);
                       memberViewHolder holder = new memberViewHolder(view);
                       return holder;
                   }
               };
       recyclerView.setAdapter(adapter);
       adapter.startListening();




    }
}