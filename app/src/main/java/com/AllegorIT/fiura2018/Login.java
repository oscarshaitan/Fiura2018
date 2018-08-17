package com.AllegorIT.fiura2018;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import io.fabric.sdk.android.Fabric;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;




public class Login extends AppCompatActivity {
    CallbackManager callbackManager;
    private VideoView myVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);
        haskey();
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_COARSE_LOCATION};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        final boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        //haskey();
        if(isLoggedIn){
            loginButton.setVisibility(View.INVISIBLE);
        }else {
            loginButton.setVisibility(View.VISIBLE);
        }


        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                graphFBData(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                System.out.println("login con fb cancel");
            }
            @Override
            public void onError(FacebookException error) {
            }
        });

        myVideoView = (VideoView)findViewById(R.id.video);

        try {

            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fiura_logo_anim));
            myVideoView.start();

            myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (isLoggedIn) {
                        retry();
                    } else {
                        loginButton.setVisibility(View.VISIBLE);
                        mp.start();
                    }
                }
            });
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    private void retry(){
        if(isConnected()){
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            graphFBData(accessToken);
        }
        else {

            Toast.makeText(getApplicationContext(),"No Internet Connection Detected",Toast.LENGTH_LONG).show();
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
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=50&heigth=50");
            String Name = object.getString("name");
            Intent intent = new Intent(this,Home2.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //logSentFriendRequestEvent();
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void haskey() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.AllegorIT.fiura2018", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                System.out.println("sha1");
                System.out.println(Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
