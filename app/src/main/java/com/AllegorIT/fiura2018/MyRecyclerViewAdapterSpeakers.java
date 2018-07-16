package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapterSpeakers extends RecyclerView
        .Adapter<MyRecyclerViewAdapterSpeakers
        .DataObjectHolder> {

    private ArrayList<ConferenceObj> mDataset;
    private static Activity activity;

    public MyRecyclerViewAdapterSpeakers(ArrayList<ConferenceObj> mDataset, Activity activity) {
        this.mDataset = mDataset;
        this.activity = activity;
    }


    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_speaker, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataObjectHolder holder, final int position) {
        holder.title.setText(mDataset.get(position).getTitle());
        holder.descript.setText(mDataset.get(position).getDescrip());
        holder.schedule.setText(mDataset.get(position).getSchedule()+" "+mDataset.get(position).getPlace());
        holder.speakerName.setText(mDataset.get(position).getSpeakerName());
        holder.rol.setText(mDataset.get(position).getRol());
        holder.idImg = mDataset.get(position).getSpeakerImg();
        Picasso.get()
                .load(holder.idImg)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.speakerImg);

        holder.pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Map.class);
                intent.putExtra("Title",mDataset.get(position).getTitle());
                intent.putExtra("Schedule",mDataset.get(position).getSchedule());
                intent.putExtra("Place",mDataset.get(position).getPlace());
                intent.putExtra("Latlang",mDataset.get(position).getLatLng());
                intent.putExtra("Speaker",mDataset.get(position).getSpeakerName());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder{
        TextView title, descript, speakerName, schedule, rol;
        ImageView speakerImg;
        int idImg;
        ImageButton pos;

        public DataObjectHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.conf_title);
            descript = (TextView) itemView.findViewById(R.id.descript);
            speakerName = (TextView) itemView.findViewById(R.id.speaker_name);
            rol = (TextView) itemView.findViewById(R.id.rol);
            schedule = (TextView) itemView.findViewById(R.id.schedule);
            speakerImg = (ImageView) itemView.findViewById(R.id.speaker_imgI);
            pos = (ImageButton)itemView.findViewById(R.id.pos);
        }
    }


}
