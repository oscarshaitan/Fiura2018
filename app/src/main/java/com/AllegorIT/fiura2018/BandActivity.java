package com.AllegorIT.fiura2018;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.AllegorIT.fiura2018.Lib.ViewAnimator;
import com.AllegorIT.fiura2018.fragment.ContentFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;

public class BandActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;

    private RecyclerView myRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band);

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

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler_view_band_activity);
        myRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapterBands(getDataSet(),this);
        myRecycler.setAdapter(mAdapter);
    }


    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.HOME, R.drawable.home);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.INFO, R.drawable.info2);
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
        SlideMenuItem menuItem8 = new SlideMenuItem(ContentFragment.SOCIAL, R.drawable.share_red);
        list.add(menuItem8);
    }

    @Override
    public void onSwitch(Resourceble slideMenuItem, int position) {
        Handler handler = new Handler();
        Intent intent = null;

        Toast.makeText(getApplicationContext(),slideMenuItem.getName(),Toast.LENGTH_SHORT).show();

        if(slideMenuItem.getName().equals(ContentFragment.CLOSE)){}
        else if(slideMenuItem.getName().equals(ContentFragment.SPEAKERS)){
            intent = new Intent(getApplication(),SpeakerActivity.class);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.OFFERS)){
            intent = new Intent(getApplication(),OffersActivity.class);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.SPONSORS)){
            intent = new Intent(getApplication(),SponsorsActivity.class);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.BANDS)){
            intent = new Intent(getApplication(),BandActivity.class);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.YOUTUBE)){
            intent = new Intent(getApplication(),YouTubeActivity.class);
        }
        else{
            intent = new Intent(getApplication(),Home2.class);
        }

        final Intent finalIntent = intent;
        if(intent != null){
            handler.postDelayed(new Runnable(){
                @Override
                public void run(){
                    startActivity(finalIntent);
                    overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
                }
            }, 800);
        }
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

    private ArrayList<BandObj> getDataSet() {
        ArrayList<BandObj> arrayList = new ArrayList<>();
        LatLng latLng = new LatLng(3.4360427,-76.5258297);
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum molestie elit, eget faucibus urna eleifend eu. Aenean aliquam commodo congue. Nulla volutpat sollicitudin justo sit amet gravida. Vivamus metus lorem, gravida a dignissim sit amet, elementum ut odio. Suspendisse rutrum ultrices risus ut pulvinar.";
        BandObj bandObj = new BandObj(R.drawable.bandphoto,s,"Ecuador","Hip-Hop","Univalle","Viernes 20 15:30","BANDAPOLIS",latLng);
        BandObj bandObj2 = new BandObj(R.drawable.bandphoto,s,"Ecuador","Hip-Hop","Univalle","Viernes 20 15:30","BANDAPOLIS",latLng);
        arrayList.add(bandObj);
        arrayList.add(bandObj2);
        arrayList.add(bandObj);
        arrayList.add(bandObj2);
        return arrayList;

    }

}
