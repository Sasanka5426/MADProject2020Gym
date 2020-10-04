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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import ViewHolderG.SupplementViewHolderAdmin;

public class MainSupplementAdmin3 extends AppCompatActivity {

    private DatabaseReference SupplemantRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;



    private String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_supplement_admin3);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            type = getIntent().getExtras().get("Admin").toString();
        }

        SupplemantRef = FirebaseDatabase.getInstance().getReference().child("Supplements");
    }

    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Supplements> options = new FirebaseRecyclerOptions.Builder<Supplements>()
                .setQuery(SupplemantRef, Supplements.class)
                .build();


        FirebaseRecyclerAdapter<Supplements,SupplementViewHolderAdmin> adapter=
                new FirebaseRecyclerAdapter<Supplements, SupplementViewHolderAdmin>(options)
                {
                    @Override
                    protected void onBindViewHolder(@NonNull final SupplementViewHolderAdmin supplementViewHolderAdmin, int i, @NonNull final Supplements supplement)
                    {
                        supplementViewHolderAdmin.txtSupplement.setText(supplement.getSupname());
                        supplementViewHolderAdmin.txtSupplementDescription.setText(supplement.getSupdes());
                        supplementViewHolderAdmin.txtSupplementPrice.setText("Price = "+supplement.getSupprice()+"Rs");
                        Picasso.get().load(supplement.getImage()).into(supplementViewHolderAdmin.imageView);




                        supplementViewHolderAdmin.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                if (type.equals("Admin"))
                                {
                                    Intent intent = new Intent(MainSupplementAdmin3.this,AdminMaintainActivity.class);
                                    intent.putExtra("supid",supplement.getSupid());
                                    startActivity(intent);

                                }
                                else
                                {
                                    Intent intent = new Intent(MainSupplementAdmin3.this,SupplementDetailsAdmin.class);
                                    intent.putExtra("supid",supplement.getSupid());
                                    startActivity(intent);
                                }

                            }
                        });
                    }


                    @NonNull
                    @Override
                    public SupplementViewHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_supplement_admin,parent,false);
                        SupplementViewHolderAdmin supplementViewHolderAdmin = new SupplementViewHolderAdmin(view);
                        return supplementViewHolderAdmin;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }










}