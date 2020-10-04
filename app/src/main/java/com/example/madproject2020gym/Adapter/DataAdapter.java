package com.example.madproject2020gym.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madproject2020gym.Model.Supplements;
import com.example.madproject2020gym.R;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    Context context;
    ArrayList<Supplements> arrayList;
    Callback callback;

    public DataAdapter(Context context, ArrayList<Supplements> arrayList, Callback callback) {
        this.context = context;
        this.arrayList = arrayList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, final int position) {
        Supplements model=arrayList.get(position);
        Glide.with(context).load(model.getImage()).into(holder.imageView);
        holder.txtName.setText(model.getSupname());
        holder.txtPrice.setText("Rs. "+model.getSupprice()+"");
        holder.txtDescription.setText(model.getSupdes());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // callback.OnClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView txtName;
        TextView txtPrice;
        TextView txtDescription;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            txtName=itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtDescription=itemView.findViewById(R.id.txtDescription);

        }
    }

}
