package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRecyclerViewAdapterBands extends RecyclerView
        .Adapter<MyRecyclerViewAdapterBands
        .DataObjectHolder> {

    private ArrayList<BandObj> mDataset;
    private static Activity activity;

    public MyRecyclerViewAdapterBands(ArrayList<BandObj> mDataset, Activity activity) {
        this.mDataset = mDataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_band, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerViewAdapterBands.DataObjectHolder holder, final int position) {

        holder.bandName.setText(mDataset.get(position).getBandName());
        holder.background.setText(mDataset.get(position).getBackground());
        holder.gender.setText(mDataset.get(position).getGender());
        holder.country.setText(mDataset.get(position).getCountry());
        holder.schedule.setText(mDataset.get(position).getSchedule()+" "+mDataset.get(position).getPlace());
        holder.idImg = mDataset.get(position).getBandImg();
        Picasso.get()
                .load(holder.idImg)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.bandImg);
        holder.pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.flag){
                    Picasso.get()
                            .load(R.drawable.circleplus)
                            .placeholder(R.drawable.redcircle)
                            .error(R.drawable.redcircle)
                            .into(holder.pos);
                    holder.superLay.setVisibility(View.GONE);
                }
                else {
                    Picasso.get()
                            .load(R.drawable.circleminus)
                            .placeholder(R.drawable.redcircle)
                            .error(R.drawable.redcircle)
                            .into(holder.pos);

                    holder.superLay.setVisibility(View.VISIBLE);
                }
                holder.flag = !holder.flag;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{
        Boolean flag = false;
        TextView country, background, bandName, schedule, gender;
        ImageView bandImg;
        int idImg;
        CircleImageView pos;
        ConstraintLayout superLay;

        public DataObjectHolder(View itemView) {
            super(itemView);

            country = (TextView) itemView.findViewById(R.id.country);
            background = (TextView) itemView.findViewById(R.id.background);
            bandName = (TextView) itemView.findViewById(R.id.band_name);
            gender = (TextView) itemView.findViewById(R.id.gender);
            schedule = (TextView) itemView.findViewById(R.id.schedule);
            bandImg = (ImageView) itemView.findViewById(R.id.band_img);
            pos = (CircleImageView)itemView.findViewById(R.id.pos);
            superLay = (ConstraintLayout) itemView.findViewById(R.id.relativeLayout5);
        }
    }
}
