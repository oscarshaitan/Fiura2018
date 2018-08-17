package com.AllegorIT.fiura2018;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.AllegorIT.fiura2018.Lib.ViewAnimator;
import com.AllegorIT.fiura2018.fragment.ContentFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;

public class Map extends AppCompatActivity implements OnMapReadyCallback, ViewAnimator.ViewAnimatorListener {

    private GoogleMap mMap;
    private String title, spkeakerName, schedule, place;
    private LatLng pos;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("Title");
        spkeakerName = bundle.getString("Speaker");
        schedule = bundle.getString("Schedule");
        place = bundle.getString("Place");
        pos = (LatLng) bundle.getParcelable("Latlang");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list,drawerLayout,this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.HOME, R.drawable.home);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.BOOK, R.drawable.info2);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.YOUTUBE, R.drawable.video);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.SPEAKERS, R.drawable.confe);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.BANDS, R.drawable.guitar);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.SPONSORS, R.drawable.bookmark);
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.OFFERS, R.drawable.sale);
        list.add(menuItem7);
        SlideMenuItem menuItem8 = new SlideMenuItem(ContentFragment.OFFERS, R.drawable.share_red);
        list.add(menuItem8);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String markerText = schedule + " " + place;

        if (schedule == null || schedule.equals("") || place == null || place.equals("")) {
            mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title(title));
        } else {
            mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title(title)
                    .snippet(markerText));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 20), 3000, null);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
        mMap.setMyLocationEnabled(true);
    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }

    @Override
    public void onSwitch(Resourceble slideMenuItem, int position) {
        Handler handler = new Handler();
        Toast.makeText(getApplicationContext(),slideMenuItem.getName(),Toast.LENGTH_SHORT).show();
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:

                Toast.makeText(getApplicationContext(),"close",Toast.LENGTH_SHORT).show();
                break;
            case ContentFragment.SPEAKERS:

                Toast.makeText(getApplicationContext(),"speakers",Toast.LENGTH_SHORT).show();

                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(getApplication(),SpeakerActivity.class);
                        startActivity(intent);
                    }
                }, 800);
                break;
            case ContentFragment.OFFERS:

                Toast.makeText(getApplicationContext(),"speakers",Toast.LENGTH_SHORT).show();

                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(getApplication(),OffersActivity.class);
                        startActivity(intent);
                    }
                }, 800);
                break;

            case ContentFragment.SPONSORS:

                Toast.makeText(getApplicationContext(),"SPONSORS",Toast.LENGTH_SHORT).show();

                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(getApplication(),SponsorsActivity.class);
                        startActivity(intent);
                    }
                }, 800);
                break;
            default:

                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(getApplication(),Home2.class);
                        startActivity(intent);
                    }
                }, 800);
                break;
        }
    }
}
