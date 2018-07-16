package com.AllegorIT.fiura2018;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class YouTubeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String[] YOUTUBE_PLAYLISTS = {
            "PLWx61XgoQmqeXh9mj47kiNIDoI9owlEpy",
            "PL8iWcNnkr_eg0g0GjH9hAGN4WQmdge9nw",
            "PL8iWcNnkr_ehIjudD5A2pFDC170oYJT60"
    };
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();
    CircleImageView profileImg;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_activity);
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
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        graphFBData(accessToken);
        if(!isConnected()){
            Toast.makeText(YouTubeActivity.this,"No Internet Connection Detected",Toast.LENGTH_LONG).show();
        }
        if (savedInstanceState == null) {
            mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                    .setApplicationName(getResources().getString(R.string.app_name))
                    .build();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, YouTubeRecyclerViewFragment.newInstance(mYoutubeDataApi, YOUTUBE_PLAYLISTS))
                    .commit();
        }
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

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
           /* System.out.println("iniciando el facebook");
            Intent intent = new Intent(this,Offers.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);*/
        }
        else if(id == R.id.speakers){
           /* System.out.println("iniciando el facebook");
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

