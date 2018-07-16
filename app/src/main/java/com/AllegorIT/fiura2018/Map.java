package com.AllegorIT.fiura2018;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class Map extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private CircleImageView profileImg;
    private TextView name;
    private String title,spkeakerName, schedule,place;
    private LatLng pos;

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

        //AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //graphFBData(accessToken);
        if(isConnected()){
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            graphFBData(accessToken);
        }
        else {
            Toast.makeText(this,"No Internet Connection Detected",Toast.LENGTH_LONG).show();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        String markerText = schedule +" "+place;

        if(schedule==null|| schedule.equals("")||place==null|| place.equals("")) {
            mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title(title));
        }
        else {
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

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void graphFBData(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                getFacebookData(object);
            }
        });
        request.executeAsync();
    }

    private void getFacebookData(JSONObject object){
        System.out.println(object.toString());
        try {
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=75&heigth=75");
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
          /*  System.out.println("iniciando el facebook");
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
