package com.AllegorIT.fiura2018;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;

public class Sponsors extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    CircleImageView profileImg;
    TextView name;

    private int[] images = {
            R.drawable.oferta1,
            R.drawable.oferta2,
            R.drawable.oferta3,
            R.drawable.fiura2018
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        profileImg = (CircleImageView) headerView.findViewById(R.id.imageView);
        name = (TextView) headerView.findViewById(R.id.name);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this, images);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);

        //AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //graphFBData(accessToken);
        if (isConnected()) {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            graphFBData(accessToken);
        } else {
            Toast.makeText(this, "No Internet Connection Detected", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void graphFBData(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                getFacebookData(object);
            }
        });
        request.executeAsync();
    }

    private void getFacebookData(JSONObject object) {
        try {
            URL profile_picture = new URL("https://graph.facebook.com/" + object.getString("id") + "/picture?width=75&heigth=75");
            Picasso.get()
                    .load(String.valueOf(profile_picture))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(profileImg);
            String Name = object.getString("name");
            name.setText(Name);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.fb) {
            System.out.println("iniciando el facebook");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/807003995983852/")));
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
        }
        else if(id == R.id.twitter){
            System.out.println("iniciando el facebook");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/FiuraCali")));
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
        }
        else if(id == R.id.youtube){
            System.out.println("iniciando el facebook");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCSwOaEBNEnXrI-AbDL8XpCQ")));
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
        }
        else if(id == R.id.insta){
            System.out.println("iniciando el facebook");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/fiuracali/")));
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
        }
        else if(id == R.id.video){
            System.out.println("iniciando el facebook");
            Intent intent = new Intent(this,YouTubeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
        }
        else if(id == R.id.home){
            System.out.println("iniciando el facebook");
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
        }
        else if(id == R.id.sales){
            /*System.out.println("iniciando el facebook");
            Intent intent = new Intent(this,Offers.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);*/
        }
        else if(id == R.id.speakers){
            /*System.out.println("iniciando el facebook");
            Intent intent = new Intent(this,Speakers.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);*/
        }
        else if(id == R.id.sponsor){
            System.out.println("iniciando el facebook");
            Intent intent = new Intent(this,Sponsors.class);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
