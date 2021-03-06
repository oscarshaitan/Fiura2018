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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.AllegorIT.fiura2018.Lib.ViewAnimator;
import com.AllegorIT.fiura2018.fragment.ContentFragment;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.login.LoginManager;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;


public class SponsorsActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    private GridView gv;
    private boolean offline;
    private Activity mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
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
        gv = findViewById(R.id.grid_view);
        gv.setAdapter(new GridAdapter(this,getDataSet()));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SponsorObj s =(SponsorObj)adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(),s.getSponsor_name(),Toast.LENGTH_SHORT).show();
            }
        });
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public ArrayList<SponsorObj> getDataSet(){
        ArrayList<SponsorObj> arrayList = new ArrayList<>();
        SponsorObj sponsorObj1 = new SponsorObj(R.drawable.la_fuente_soda, new LatLng(3.397863, -76.539862),"La Fuente de Soda");
        SponsorObj sponsorObj2 = new SponsorObj(R.drawable.altavoz, new LatLng(3.397863, -76.539862),"Altavoz");
        SponsorObj sponsorObj3 = new SponsorObj(R.drawable.agente, new LatLng(3.397863, -76.539862),"Agente Naranja");
        SponsorObj sponsorObj4 = new SponsorObj(R.drawable.barloventus, new LatLng(3.397863, -76.539862),"Barloventus Bar");
        SponsorObj sponsorObj5 = new SponsorObj(R.drawable.cali_tatto2, new LatLng(3.397863, -76.539862),"Cali Tatto");
        SponsorObj sponsorObj6 = new SponsorObj(R.drawable.carpa_intolerancia, new LatLng(3.397863, -76.539862),"Carpa Intolerancia");
        SponsorObj sponsorObj7 = new SponsorObj(R.drawable.lo_mundano, new LatLng(3.397863, -76.539862),"Lo mundano");
        SponsorObj sponsorObj8 = new SponsorObj(R.drawable.logo_garra, new LatLng(3.397863, -76.539862),"Garra Producciones");
        SponsorObj sponsorObj9 = new SponsorObj(R.drawable.nuestro_bar, new LatLng(3.397863, -76.539862),"Nuestro Bar");
        SponsorObj sponsorObj10 = new SponsorObj(R.drawable.el_faro, new LatLng(3.397863, -76.539862),"El Faro Pizzeria Limonar");
        SponsorObj sponsorObj11 = new SponsorObj(R.drawable.festivalfff, new LatLng(3.397863, -76.539862),"Festivalfff");
        SponsorObj sponsorObj12 = new SponsorObj(R.drawable.ibague_c_r, new LatLng(3.397863, -76.539862),"Ibagué ciudad rock");
        SponsorObj sponsorObj13 = new SponsorObj(R.drawable.indie_fest, new LatLng(3.397863, -76.539862),"Indie fest");
        SponsorObj sponsorObj14 = new SponsorObj(R.drawable.rockopolis, new LatLng(3.397863, -76.539862),"Rockopolis");
        SponsorObj sponsorObj15 = new SponsorObj(R.drawable.ev_backline, new LatLng(3.397863, -76.539862),"ev backline");
        SponsorObj sponsorObj16 = new SponsorObj(R.drawable.blue_hell, new LatLng(3.397863, -76.539862),"Blue Hell");
        SponsorObj sponsorObj17 = new SponsorObj(R.drawable.amor_fe_logo, new LatLng(3.397863, -76.539862),"Amor y fe");
        SponsorObj sponsorObj18 = new SponsorObj(R.drawable.madame_b, new LatLng(3.397863, -76.539862),"Fundación Madame Blue");
        SponsorObj sponsorObj19 = new SponsorObj(R.drawable.jaguar, new LatLng(3.397863, -76.539862),"Festival Jaguar");
        SponsorObj sponsorObj20 = new SponsorObj(R.drawable.rappi, new LatLng(3.397863, -76.539862),"Rappi");

        arrayList.add(sponsorObj1);
        arrayList.add(sponsorObj2);
        arrayList.add(sponsorObj3);
        arrayList.add(sponsorObj4);
        arrayList.add(sponsorObj5);
        arrayList.add(sponsorObj6);
        arrayList.add(sponsorObj7);
        arrayList.add(sponsorObj8);
        arrayList.add(sponsorObj9);
        arrayList.add(sponsorObj20);
        arrayList.add(sponsorObj10);
        arrayList.add(sponsorObj11);
        arrayList.add(sponsorObj12);
        arrayList.add(sponsorObj13);
        arrayList.add(sponsorObj14);
        arrayList.add(sponsorObj15);
        arrayList.add(sponsorObj16);
        arrayList.add(sponsorObj17);
        arrayList.add(sponsorObj18);
        arrayList.add(sponsorObj19);
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

        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.INFO, R.drawable.info2);
        list.add(menuItem2);
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
        else if(slideMenuItem.getName().equals(ContentFragment.INFO)){
            intent[0] = new Intent(getApplication(),Info.class);
            intent[0].putExtra("offline", offline);
        }
        else if(slideMenuItem.getName().equals(ContentFragment.OFFERS)){
            if(offline){
                Toast.makeText(this,R.string.log_to_offer,Toast.LENGTH_LONG).show();
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
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("youtube://user/channel/UCSwOaEBNEnXrI-AbDL8XpCQ")));
                overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCSwOaEBNEnXrI-AbDL8XpCQ")));
                overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
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
                    Toast.makeText(getApplicationContext(),R.string.need_msn,Toast.LENGTH_SHORT).show();
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
                    .content(R.string.sure_logout)
                    .positiveText(R.string.continue_btn)
                    .negativeText(R.string.cancel_btn)
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

    @Override
    public void onBackPressed(){

    }
}