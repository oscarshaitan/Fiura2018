package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class SponsorsGrid {
    private GridView gv;
    private Activity activity;
    private View view;

    public SponsorsGrid(Activity activity) {
        this.activity = activity;
        view = LayoutInflater.from(
                activity.getBaseContext()).inflate(R.layout.sponsors_grid, null, false);

        init();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void init(){
        gv = view.findViewById(R.id.grid_view);
        gv.setAdapter(new GridAdapter(activity,getDataSet()));
    }

    public ArrayList<SponsorObj> getDataSet(){
        SponsorObj sponsorObj = new SponsorObj(R.drawable.faro_logo, new LatLng(3.397863, -76.539862),"El Faro Pizzeria Limonar");
        ArrayList<SponsorObj> arrayList = new ArrayList<>();
        arrayList.add(sponsorObj);
        arrayList.add(sponsorObj);
        arrayList.add(sponsorObj);
        arrayList.add(sponsorObj);
        arrayList.add(sponsorObj);
        arrayList.add(sponsorObj);
        arrayList.add(sponsorObj);
        arrayList.add(sponsorObj);
        arrayList.add(sponsorObj);
        return arrayList;
    }
}
