package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class Home2 extends Activity {
    private Activity activity;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        initUI();
        activity = this;
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_vertical_ntb);


        //final String[] colors = getResources().getStringArray(R.array.vertical_ntb);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_vertical);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.home),
                        Color.parseColor(getResources().getString(0+R.color.colorAccent)))
                        .title("Home")
                        .selectedIcon(getResources().getDrawable(R.drawable.home))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.info2),
                        Color.parseColor(getResources().getString(0+R.color.colorAccent)))
                        .selectedIcon(getResources().getDrawable(R.drawable.info2))
                        .title("Info")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.video),
                        Color.parseColor(getResources().getString(0+R.color.colorAccent)))
                        .selectedIcon(getResources().getDrawable(R.drawable.video))
                        .title("Video")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.confe),
                        Color.parseColor(getResources().getString(0+R.color.colorAccent)))
                        .selectedIcon(getResources().getDrawable(R.drawable.confe))
                        .title("Speakers")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.guitar),
                        Color.parseColor(getResources().getString(0+R.color.colorAccent)))
                        .selectedIcon(getResources().getDrawable(R.drawable.guitar))
                        .title("Bands")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.bookmark),
                        Color.parseColor(getResources().getString(0+R.color.colorAccent)))
                        .selectedIcon(getResources().getDrawable(R.drawable.bookmark))
                        .title("Sponsors")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.sale),
                        Color.parseColor(getResources().getString(0+R.color.colorAccent)))
                        .selectedIcon(getResources().getDrawable(R.drawable.sale))
                        .title("Offers")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_share),
                        Color.parseColor(getResources().getString(0+R.color.colorAccent)))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_share))
                        .title("SocialNets")
                        .build()
        );

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return models.size();
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view;

                switch (position){

                    case 3:
                        SpeakerList speakers = new SpeakerList(activity);
                        view = speakers.getView();
                        break;
                    case 5:
                        SponsorsGrid sponsorsGrid = new SponsorsGrid(activity);
                        view = sponsorsGrid.getView();
                        break;
                    case 6:
                        OffersList offersList = new OffersList(activity);
                        view = offersList.getView();
                        break;
                    case 7:
                        SocialNetObj socialNetObj = new SocialNetObj(activity);
                        view = socialNetObj.getView();
                        break;

                    default:
                        view = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.item_vp2, null, false);
                        final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
                        txtPage.setText("Page #"+position);
                }
                container.addView(view);
                return view;
            }
        });
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 5){
                    Toast.makeText(activity,"Toca un sponsor si quieres saber mas!!!",Toast.LENGTH_SHORT).show();
                }
                if(position==6){
                    Toast.makeText(activity,"Toca una oferta si quieres saber mas!!!",Toast.LENGTH_SHORT).show();
                }
                if(position==7){
                    Toast.makeText(activity,"Toca uno de los iconos para ir a nuestras redes sociales",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}

