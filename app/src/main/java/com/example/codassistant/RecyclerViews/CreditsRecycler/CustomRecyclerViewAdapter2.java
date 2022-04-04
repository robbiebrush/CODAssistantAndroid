package com.example.codassistant.RecyclerViews.CreditsRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codassistant.Database.pojos.Credit;
import com.example.codassistant.R;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter2 extends RecyclerView.Adapter<CustomViewHolder> {
    private ArrayList<Credit> credits;

    public CustomRecyclerViewAdapter2(ArrayList<Credit> credits) {
        this.credits = credits;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.credits_recycler_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Credit credit = credits.get(position);
        holder.creditFor.setText(credit.getCreditFor());
        holder.creditTo.setText(credit.getCreditTo());
    }

    @Override
    public int getItemCount() {
        if(credits != null){
            return credits.size();
        }
        return 0;
    }
}

class CustomViewHolder extends RecyclerView.ViewHolder{
    protected TextView creditFor;
    protected TextView creditTo;

    public CustomViewHolder(@NonNull View itemView) { //recycle_row
        super(itemView);
        this.creditFor = itemView.findViewById(R.id.creditFor);
        this.creditTo = itemView.findViewById(R.id.creditTo);
    }
}
