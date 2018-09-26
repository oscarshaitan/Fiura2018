package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class VideoBandas extends AppCompatActivity {

    private  VideoView myVideoView;
    private boolean offline;
    private Activity mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_bandas);
        Bundle bundle = getIntent().getExtras();
        mContext = this;
        offline = bundle.getBoolean("offline");



        myVideoView = (VideoView)findViewById(R.id.video);
        try {

            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bandas_locales));
            myVideoView.start();

            myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                    new MaterialDialog.Builder(mContext)
                            .title(R.string.replay)
                            .content(R.string.repeat)
                            .positiveText(R.string.continue_btn)
                            .negativeText(R.string.cancel_btn)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    myVideoView.start();
                                }
                            })
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    Toast.makeText(getApplicationContext(),R.string.go_repeat,Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),Home2.class);
                                    intent.putExtra("offline", offline);
                                    startActivity(intent);
                                    overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
                                }
                            })
                            .show();


                }
            });

        }catch (Exception e){
            e.getStackTrace();
        }
        final TextView skip = (TextView)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myVideoView.stopPlayback();
                Toast.makeText(getApplicationContext(),R.string.go_repeat,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Home2.class);
                intent.putExtra("offline", offline);
                startActivity(intent);
                overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
