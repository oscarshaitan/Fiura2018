package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SocialNetObj{
    ImageView fb, msn, inst, twtr, ytb;
    Activity activity;
    View view;

    public SocialNetObj(final Activity activity) {
        this.activity = activity;
        view = LayoutInflater.from(
                activity.getBaseContext()).inflate(R.layout.social_net, null, false);

        fb = (ImageView)view.findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/807003995983852/")));
                }catch (Exception e){
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/unirock.alternativo/")));
                }
                activity.overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        });
        msn = (ImageView)view.findViewById(R.id.msn);
        msn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://messaging/807003995983852/")));
                } catch (Exception e) {
                    try {
                        Toast.makeText(activity.getApplicationContext(), "Need Messenger installed to do that!!!!", Toast.LENGTH_SHORT).show();
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.facebook.orca")));
                    } catch (Exception e2) {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.facebook.orca")));
                    }
                }
                activity.overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        });
        twtr = (ImageView)view.findViewById(R.id.twtr);
        twtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/FiuraCali")));
                activity.overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        });
        inst = (ImageView)view.findViewById(R.id.insta);
        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/fiuracali/")));
                activity.overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        });
        ytb = (ImageView)view.findViewById(R.id.ytb);
        ytb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCSwOaEBNEnXrI-AbDL8XpCQ")));
                activity.overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        });
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
