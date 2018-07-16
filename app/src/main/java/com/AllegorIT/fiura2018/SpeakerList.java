package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class SpeakerList {
    private Activity activity;
    private RecyclerView myRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    public SpeakerList(Activity activity) {
        this.activity = activity;
        view = LayoutInflater.from(
                activity.getBaseContext()).inflate(R.layout.speakers_list, null, false);
        init ();
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private ArrayList<ConferenceObj> getDataSet() {
        ArrayList<ConferenceObj> arrayList = new ArrayList<>();
        LatLng latLng = new LatLng(3.4360427,-76.5258297);
        ConferenceObj conferenceObj = new ConferenceObj(R.drawable.vertical_photo_profile,"Creación de Bandas",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus quis arcu vitae neque consequat rhoncus. Sed hendrerit felis maximus ante bibendum porttitor. Vestibulum dignissim orci in sodales facilisis. In elit nisl, tempus in lectus id, elementum luctus ante. Nulla facilisi. Vestibulum vel libero dictum",
                "Auditorio 5","15/09/2018 15:30","Pedro Perez", "VOCAL",latLng);
        arrayList.add(conferenceObj);
        arrayList.add(conferenceObj);
        arrayList.add(conferenceObj);
        arrayList.add(conferenceObj);
        return arrayList;
    }

    private void init (){
        myRecycler = (RecyclerView) view.findViewById(R.id.my_recycler_view_speakers);
        myRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        myRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapterSpeakers(getDataSet(),activity);
        myRecycler.setAdapter(mAdapter);
    }

    public View getView(){
        return view;
    }


}
