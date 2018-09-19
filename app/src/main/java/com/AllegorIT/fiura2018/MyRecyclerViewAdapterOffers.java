package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapterOffers extends RecyclerView
        .Adapter<MyRecyclerViewAdapterOffers
        .DataObjectHolder>{

    private ArrayList<OffersObj> mDataset;
    private static Activity activity;

    public MyRecyclerViewAdapterOffers(ArrayList<OffersObj> mDataset, Activity activity) {
        this.mDataset = mDataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_offers, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataObjectHolder holder, final int position) {

        holder.promo.setImageDrawable(activity.getResources().getDrawable(mDataset.get(position).getOffers_img()));

       /* Picasso.get()
                .load(mDataset.get(position).getOffers_sponsor())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.sponsor_logo);

        Picasso.get()
                .load(mDataset.get(position).getOffers_img())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.promo);*/

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{
        ImageView sponsor_logo, promo;
        int idImgSpo,idImgPro;
        ConstraintLayout card;

        public DataObjectHolder(View itemView) {
            super(itemView);
            //sponsor_logo = (ImageView) itemView.findViewById(R.id.sponsor_logo);
            card = (ConstraintLayout) itemView.findViewById(R.id.card_offer);
            promo = (ImageView) itemView.findViewById(R.id.promo);
        }
    }
}
