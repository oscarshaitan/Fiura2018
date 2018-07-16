package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class OffersList {
    private Activity activity;
    private RecyclerView myRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    public OffersList(Activity activity) {
        this.activity = activity;
        view = LayoutInflater.from(
                activity.getBaseContext()).inflate(R.layout.offers_list, null, false);

        init ();
    }

    private ArrayList<OffersObj> getDataSet() {
        ArrayList<OffersObj> arrayList = new ArrayList<>();
        LatLng latLng = new LatLng(3.3980351,-76.5258297);
        OffersObj offersObj = new OffersObj(R.drawable.faro_promo,R.drawable.faro_logo, new LatLng(3.397863, -76.539862),"El Faro Pizzeria Limonar");
        arrayList.add(offersObj);
        arrayList.add(offersObj);
        arrayList.add(offersObj);
        arrayList.add(offersObj);
        return arrayList;
    }

    private void init (){
        myRecycler = (RecyclerView) view.findViewById(R.id.my_recycler_view_offers);
        myRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        myRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapterOffers(getDataSet(),activity);
        myRecycler.setAdapter(mAdapter);
    }

    public View getView(){
        return view;
    }
}
