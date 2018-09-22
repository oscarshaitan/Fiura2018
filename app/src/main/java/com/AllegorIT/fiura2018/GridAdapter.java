package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.widget.ImageView.ScaleType.CENTER_CROP;
import static com.facebook.FacebookSdk.getApplicationContext;

final class GridAdapter extends BaseAdapter {
    private Activity activity;
    private List<SponsorObj> sponsors;

    public GridAdapter(Activity activity, ArrayList<SponsorObj> dataSet) {
        this.activity = activity;
        this.sponsors = dataSet;
    }

    @Override
    public int getCount() {
        return sponsors.size();
    }

    @Override
    public Object getItem(int position) {
        return sponsors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = activity.getApplicationContext();
        View view = new View(context);

        view = LayoutInflater.from(
                activity.getBaseContext()).inflate(R.layout.grid_cell, null, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.cell_imag);
        imageView.setScaleType(CENTER_CROP);
        imageView.setPadding(0,2,0,0);

        // Get the image URL for the current position.
        int sponsorId = sponsors.get(position).getSponsor_img();

        // Trigger the download of the URL asynchronously into the image view.
        Picasso picasso = new Picasso.Builder(getApplicationContext())
                .indicatorsEnabled(false)
                .loggingEnabled(false) //add other settings as needed
                .build();

        Picasso.get() //
                .load(sponsorId) //
                .placeholder(R.drawable.ic_launcher_background) //
                //.error(R.drawable.error) //
                .fit() //
                .tag(context) //
                .into(imageView);
        return view;
    }
}
