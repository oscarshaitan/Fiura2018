package com.AllegorIT.fiura2018;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;

import com.AllegorIT.fiura2018.Lib.ViewAnimator;
import com.AllegorIT.fiura2018.fragment.ContentFragment;
import com.google.android.gms.maps.model.LatLng;


public class SponsorsActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    private GridView gv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
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
        gv = findViewById(R.id.grid_view);
        gv.setAdapter(new GridAdapter(this,getDataSet()));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SponsorObj s =(SponsorObj)adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), Map.class);
                intent.putExtra("Title",s.getSponsor_name());
                intent.putExtra("Latlang",s.getPos());
                startActivity(intent);
                overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        });
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
}