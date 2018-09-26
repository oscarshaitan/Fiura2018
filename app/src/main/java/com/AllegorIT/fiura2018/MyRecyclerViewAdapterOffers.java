package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyRecyclerViewAdapterOffers extends RecyclerView
        .Adapter<MyRecyclerViewAdapterOffers
        .DataObjectHolder>{

    private ArrayList<OffersObj> mDataset;
    private Activity activity;

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
                if(mDataset.get(position).getSponsor_name().equals("Agente Naranja")){
                    activity.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.agentenaranja.co/?coupon_code=promofiura")));
                }
                if(mDataset.get(position).getSponsor_name().equals("Tu zona ticket y FIURA")){
                    activity.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://pro.comprastuzonaticket.com/carrito/?id=106")));
                }
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
