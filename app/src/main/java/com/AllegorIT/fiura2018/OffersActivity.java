package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;

import com.AllegorIT.fiura2018.Lib.ViewAnimator;
import com.AllegorIT.fiura2018.fragment.ContentFragment;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.login.LoginManager;
import com.google.android.gms.maps.model.LatLng;


public class OffersActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    private RecyclerView myRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean offline;
    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        Bundle bundle = getIntent().getExtras();
        mContext = this;
        offline = bundle.getBoolean("offline");
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
        myRecycler = (RecyclerView) findViewById(R.id.my_recycler_view_offers);
        myRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapterOffers(getDataSet(),this);
        myRecycler.setAdapter(mAdapter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private ArrayList<OffersObj> getDataSet() {
        ArrayList<OffersObj> arrayList = new ArrayList<>();
        OffersObj offersObj = new OffersObj(R.drawable.la_fuente_promo,R.drawable.la_fuente_soda, new LatLng(3.4709375,-76.5270671),"La fuente de soda");
        OffersObj offersObj2 = new OffersObj(R.drawable.bandphoto_promo,R.drawable.barloventus, new LatLng(3.4853537,-76.5033645),"Barloventus");
        arrayList.add(offersObj);
        arrayList.add(offersObj2);
        arrayList.add(offersObj);
        arrayList.add(offersObj2);
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
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.YOUTUBE, R.drawable.youtube);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.SPEAKERS, R.drawable.confe);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.BANDS, R.drawable.guitar);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.SPONSORS, R.drawable.bookmark);
        list.add(menuItem6);
        if(offline){
            SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.OFFERS, R.drawable.sale_off);
            list.add(menuItem7);
        }
        else{
            SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.OFFERS, R.drawable.sale);
            list.add(menuItem7);
        }


        SlideMenuItem menuItem8 = new SlideMenuItem(ContentFragment.FACEBOOK, R.drawable.fb);
        list.add(menuItem8);
        SlideMenuItem menuItem9 = new SlideMenuItem(ContentFragment.MESSENGER, R.drawable.messenger);
        list.add(menuItem9);
        SlideMenuItem menuItem10 = new SlideMenuItem(ContentFragment.INSTAGRAM, R.drawable.instagram);
        list.add(menuItem10);
        SlideMenuItem menuItem11 = new SlideMenuItem(ContentFragment.TWITTER, R.drawable.twitter);
        list.add(menuItem11);
        SlideMenuItem menuItem12 = new SlideMenuItem(ContentFragment.LOGOUT, R.drawable.logout);
        list.add(menuItem12);
    }

    @Override
    public void onSwitch(Resourceble slideMenuItem, int position) {
        Handler handler = new Handler();
        final Intent[] intent = {null};

        if(slideMenuItem.getName().equals(ContentFragment.CLOSE)){}
        else if(slideMenuItem.getName().equals(ContentFragment.SPEAKERS)){
            intent[0] = new Intent(getApplication(),SpeakerActivity.class);
            intent[0].putExtra("offline", offline);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.OFFERS)){
            if(offline){
                Toast.makeText(this,"You must be login to get the offers!!!",Toast.LENGTH_LONG).show();
            }
            else {
                intent[0] = new Intent(getApplication(),OffersActivity.class);
                intent[0].putExtra("offline", offline);
            }

        }
        else if(slideMenuItem.getName().equals(ContentFragment.SPONSORS)){
            intent[0] = new Intent(getApplication(),SponsorsActivity.class);
            intent[0].putExtra("offline", offline);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.BANDS)){
            intent[0] = new Intent(getApplication(),BandActivity.class);
            intent[0].putExtra("offline", offline);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.YOUTUBE)){
            intent[0] = new Intent(getApplication(),YouTubeActivity.class);
            intent[0].putExtra("offline", offline);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.FACEBOOK)){
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/807003995983852/")));
                overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }catch (Exception e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/unirock.alternativo/")));
                overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        }
        else if (slideMenuItem.getName().equals(ContentFragment.MESSENGER)) {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://messaging/807003995983852/")));
            }catch (Exception e){
                try{
                    Toast.makeText(getApplicationContext(),"Need Messenger installed to do that!!!!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.facebook.orca")));
                    overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
                }
                catch (Exception e2){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.facebook.orca")));
                    overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
                }
            }
        }
        else if(slideMenuItem.getName().equals(ContentFragment.TWITTER)){
            try {
                Intent intent2 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("twitter://user?user_id=2288881418"));
                startActivity(intent2);
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/FiuraCali")));
            }
        }
        else if(slideMenuItem.getName().equals(ContentFragment.INSTAGRAM)){
            Uri uri = Uri.parse("http://instagram.com/_u/fiuracali");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/fiuracali/")));
            }
        }
        else if (slideMenuItem.getName().equals(ContentFragment.LOGOUT)) {
            new MaterialDialog.Builder(mContext)
                    .title("Logout")
                    .content("Sure you wanna logout?")
                    .positiveText("Continue")
                    .negativeText("Cancel")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            LoginManager.getInstance().logOut();
                            intent[0] = new Intent(getApplication(), Login.class);
                            startActivity(intent[0]);
                            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
                        }
                    })
                    .show();
        }

        else{
            intent[0] = new Intent(getApplication(),Home2.class);
            intent[0].putExtra("offline", offline);
        }

        final Intent finalIntent = intent[0];
        if(intent[0] != null){
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