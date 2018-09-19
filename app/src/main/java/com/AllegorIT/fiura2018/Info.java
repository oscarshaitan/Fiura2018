package com.AllegorIT.fiura2018;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;

import com.AllegorIT.fiura2018.Lib.ViewAnimator;
import com.AllegorIT.fiura2018.fragment.ContentFragment;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.login.LoginManager;

import org.w3c.dom.Text;


public class Info extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    private boolean offline;
    private VideoView myVideoView;
    private Activity mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle bundle = getIntent().getExtras();
        offline = bundle.getBoolean("offline");
        mContext = this;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        viewAnimator = new ViewAnimator<>(this, list,drawerLayout,this);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        setActionBar();
        createMenuList();


        TextView info = (TextView) findViewById(R.id.Info);
        String infoText = "Recomendaciones para el FIURA 2018\n\n" +
                "•\tEl ingreso es para personas mayores de 18 años. \n" +
                "•\tQueda restringido el ingreso de accesorios con taches, cadenas, reatas, astas de banderas, tablas de skate, palos, envases de ningún tipo, armas blancas, armas de fuego, objetos contundentes, cortopunzantes o cualquier otro elemento que pueda lesionar a los asistentes al Festival. \n" +
                "•\tReconozcan muy bien las salidas de evacuación y los equipos de primeros auxilios que se encuentran el lugar del concierto. \n" +
                "•\tIdentifiquen las zonas de baños. \n" +
                "•\tAcuerden siempre un punto de encuentro por si se pierde algunos de sus acompañantes. \n" +
                "\n" +
                " ¿ES LA PRIMERA VEZ QUE VIENE AL FIURA? \n" +
                "\n" +
                "•\tEste festival se realiza en la Universidad del Valle Sede Meléndez, por lo tanto, al estar situado en un Campus Universitario NO está autorizado ingreso de vehículos de público en general, tours o artistas locales. \n" +
                "•\tTodas las personas, incluidos sus maletines, bolsos, canguros, etc, serán requisadas en el primer anillo de seguridad, cualquier elemento restringido será retenido antes de ingresar al FIURA. \n" +
                "•\tNo está permitido acampar dentro del campus, ni en zonas contiguas a la Universidad del Valle. \n" +
                "\n" +
                "PARA CONVIVIR EN PAZ \n\n" +
                "\n" +
                "•\tNo se permite el ingreso de personas en visible estado de embriaguez o bajo efectos de sustancia psicoactivas o alucinógenas. Igualmente, no se permitirá el ingreso de ningún tipo de bebidas alcohólicas. \n" +
                "•\tNo se permite ingresar, beber, inhalar, fumar, inyectarse o consumir de alguna otra forma, cualquier tipo de sustancias alcohólicas, alucinógenas, enervantes, psicodélicas, disociativas, delirantes, opioides, sedantes, anestésicas, hipnóticas, euforizantes, estimulantes, depresivas, y demás que se consideren ilícitas, en cualquier zona dentro del Festival. \n" +
                "•\tDebe ir bien alimentado (a) o llevar dinero, los puestos de venta de alimentación e hidratación estarán en servicio durante todo el evento. Por lo tanto, NO se permite el ingreso de alimentos y ni bebidas de ningún tipo. \n" +
                "•\tSi están en el “pogo” y alguien se cae, lo auxiliamos y ayudamos a parar. \n" +
                "•\tEste atento a todas las recomendaciones de seguridad realizadas por parte de los organizadores y presentadores del evento, así como del personal de Logística. \n" +
                "•\tNo se permite el ingreso al evento vistiendo camisetas alusivas a cualquier equipo de fútbol, o elementos alusivos a estos.\n" +
                "•\tDebe portar documento de identidad y carnet de afiliación EPS, esto en caso que se presentara algún incidente que requiera atención médica prioritaria. \n" +
                "•\tSe recomienda a las mujeres en estado de embarazo cuidar de su integridad física manteniéndose a una distancia segura del “pogo”.  \n" +
                "\n" +
                "BIENVENIDO AL #FIURA2018\n" +
                "\n" +
                "•\tRecuerde que el FIURA es una fiesta para la sana convivencia, la apertura y el respeto por las diferentes expresiones artísticas. Se espera que el público llegue cargado de alegría, entusiasmo, respeto y tolerancia para disfrutar al máximo de la diversidad musical de la región y nuestros invitados Nacionales e Internacionales.\n";
        info.setText(infoText);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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