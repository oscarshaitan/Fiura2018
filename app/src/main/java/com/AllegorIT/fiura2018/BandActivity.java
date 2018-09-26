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

public class BandActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {

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
        setContentView(R.layout.activity_band);
        Bundle bundle = getIntent().getExtras();
        offline = bundle.getBoolean("offline");
        mContext = this;
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

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler_view_band_activity);
        myRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecycler.setLayoutManager(mLayoutManager);
        if(getDataSet().size()==0)Toast.makeText(getApplicationContext(), R.string.bands_soon,Toast.LENGTH_LONG).show();
        mAdapter = new MyRecyclerViewAdapterBands(getDataSet(),this);
        myRecycler.setAdapter(mAdapter);
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

    private ArrayList<BandObj> getDataSet() {
        ArrayList<BandObj> arrayList = new ArrayList<>();
        LatLng latLng = new LatLng(3.371430, -76.530339);

       arrayList.add(new BandObj(R.drawable.legend_maker,
                "LEGEND MAKER es una banda de Heavy Power Metal fundada en Cali, con más de 20 años trayectoria musical. Con estilo veloz y melódico, la banda ha desarrollado un sonido potente donde el despliegue técnico de los músicos aflora para deleitar al público con canciones influenciadas por el neoclasicismo y el rock progresivo.","Colombia",
                "Heavy Power Metal","Univalle","---- --/-- --:--",
                "Legend Maker",latLng));

        arrayList.add(new BandObj(R.drawable.skaimanes,
                "16 años","Colombia",
                "Ska/Reggae/Rock&Roll","Univalle","---- --/-- --:--",
                "Skaimanes",latLng));

        arrayList.add(new BandObj(R.drawable.ra_la_culebra,
                "A Ra La Culebra  la soportan: 12 años de  carrera Artistica-10 Giras Internacionales- Premio Subterranica a la mejor  banda alternativa 2015- 4 Trabajos discográficos- Reseñados en Radionica por su poder de autogestión.- Show impactante con un alto grado performático  y la producción de su último trabajo por Tweety Gonzales","Colombia",
                "Rock Culebrero","Univalle","---- --/-- --:--",
                "RA- LA CULEBRA",latLng));

        arrayList.add(new BandObj(R.drawable.vientre,
                "Vientre es una banda de Post Hardcore creada en el 2016 en Cali. En el 2017 la banda lanzó su primer EP \"Las Huellas que Dejamos\" con el cual  viajó a través de las principales ciudades de Colombia, Ecuador y México. \"Semillas\" es su primer trabajo de larga duración y será lanzado en Octubre de 2018.","Colombia",
                "Post Hardcore","Univalle","---- --/-- --:--",
                "Vientre",latLng));

        arrayList.add(new BandObj(R.drawable.red_sun_cult,
                "Red Sun Cult es una banda de Cali con un sonido fuertemente influenciado por el hard rock y el heavy psych. Poseen en su recorrido dos giras nacionales y dos internacionales al igual que varios festivales en Colombia. Su propuesta ha sido bien recibida por la crítica especializada en el exterior.","Colombia",
                "Heavy Psych/Stoner Rock","Univalle","---- --/-- --:--",
                "Red Sun Cult",latLng));

        arrayList.add(new BandObj(R.drawable.los_hotpants,
                "Banda de Indie Rock de la ciudad de Cali., inspirado en el rock de los 90`s (Shoegaze, Grunge, Indie, Post Rock), Ha participado en diferentes mercados musicales de Colombia, alternado con bandas como Babasonicos, Austin Tv, Festival Estéreo Picnic, entre otros tanto nacional e internacional.  ","Colombia",
                "Indie Rock","Univalle","---- --/-- --:--",
                "Los Hotpants",latLng));

        arrayList.add(new BandObj(R.drawable.gutgrinder,
                "Fundada en el año 2011,quienes en su corto  recorrido han logrado enlistarse en el top de los seguidores del buen metal extremo, convirtiéndose en uno de los actos más importantes y contundentes del metal actual en el país. Presentan su album debut \"A Prophecy of Sacrilege\" lanzado en 2017 bajo el sello Hateworks","Colombia",
                "Death Metal","Univalle","---- --/-- --:--",
                "Gutgrinder",latLng));

        arrayList.add(new BandObj(R.drawable.sekuas,
                "Banda de Punk rock HC de la ciudad de cali-colombia que inicia su recorrido en el año 2004 manteniendo desde entonces la misma aliciacion musical." +
                        "SEKUAS se ha caracterizado por hacer música propia y original, queriendo siempre tener una identidad y un estilo diferenciador ","Colombia",
                "Punk Rock","Univalle","---- --/-- --:--",
                "Sekuas",latLng));

        arrayList.add(new BandObj(R.drawable.david_la_facha,
                "De 2010 a la fecha. Desde 2017 retomando el proyecto musical. David y La Facha Ha tocado en ciudades como Cali, Pasto, Popayan, Tuluá, Armenia, Salento, Sevilla y Pereira. Actualment tiene 4 singles en las diferentes plataformas digitales de música y está preparando el lanzamiento de 2 vídeos oficiales.","Colombia",
                "Funk Rock","Univalle","---- --/-- --:--",
                "David y La facha",latLng));

        arrayList.add(new BandObj(R.drawable.anhedonia,
                "A lo largo de su trayectoria, Anhedonia cuenta con un EP, un single, dos videoclips y actualmente se encuentra en la producción de un álbum y un videoclip que será estrenado antes de finalizar el año.","Colombia",
                "Post hardcore/Screamo","Univalle","---- --/-- --:--",
                "Anhedonia",latLng));

        arrayList.add(new BandObj(R.drawable.l_c_d,
                "LCD inicia en el año 2005 . Graba su primer trabajo en el 2014 titulado \"A un dia de mi muerte\". Punk Cali .","Colombia",
                "Punk","Univalle","---- --/-- --:--",
                "L.C.D. (La Colombia Decadente)",latLng));

        arrayList.add(new BandObj(R.drawable.judio,
                "16 años","Colombia",
                "Rap dark side ","Univalle","---- --/-- --:--",
                "Rap JUDIO de matanza danza",latLng));


/*


        arrayList.add(new BandObj(R.drawable.surviving,
                "SURVIVING nace en 2012 con diferentes influencias de sus integrantes que proceden de varias bandas colombianas reconocidas, consolidando así un sonido sólido que le han permitido un respetado lugar en la escena de metalcore nacional. Actualmente en grabación del segundo trabajo y posterior gira latinoamericana 2019","Colombia",
                "Metalcore","Univalle","---- --/-- --:--",
                "Surviving",latLng));

        arrayList.add(new BandObj(R.drawable.repxblica_cxervos,
                "Repxblica de Cxervos es una banda bogotana formada en 2013 por varios músicos con trayectoria en la escena nacional. Su música es una declaración, un manifesto contra la realidad distópica que se ha hecho viral, usa elementos como la rabia como motor creativo. www.republicadecuervos.com","Colombia",
                "Rock","Univalle","---- --/-- --:--",
                "Repxblica de Cxervos",latLng));

        arrayList.add(new BandObj(R.drawable.militantex,
                "Militantex es experimental, crudo y contemporáneo y expresan con su lenguaje la visión de las calles que habitan. Con un trabajo destacado de más de 60 shows en 2017, incluyendo fechas en Bogotá, Medellín, el Valle, el eje Cafetero entre otros. Además, realizan sus primeras giras internacionales a Ecuador y México.","Colombia",
                "NoJazz","Univalle","---- --/-- --:--",
                "Militantex",latLng));


        arrayList.add(new BandObj(R.drawable.psychopath_billy,
                "El Psychopath Billy nace a mediados de noviembre del 2008 en la ciudad de Palmira, Como una banda Psychobilly conformado por: Ruderocker Voz y Guitarra, GG el gallo Gutierrez Baterista y Mizinga Bajo. Cuenta con presentaciones en conciertos y festivales de diferentes partes del país y del exterior.","Colombia",
                "psychobilly ","Univalle","---- --/-- --:--",
                "psychopath billy",latLng));

        arrayList.add(new BandObj(R.drawable.rmk,
                "Proyecto electrónico, con influencias de bigbeat, drum n bass, world music, breackbeat. Creado en Meixco en 2015 por Christian de la espriella (productor musical, conocido por su proyecto de rock, pornomotora.)La música de RUA MAKHIL-A, esta creada en su mayor parte basándose en sampling, y poniendo en primer plano, la fuerza de los vientos (trompetas y trombones), sobre bases rítmicas folclóricas como el mapale, y  dancefloor , como el drum n bass.","Colombia",
                "Big beat/Drum n Bass/World music ","Univalle","---- --/-- --:--",
                "RUA MAKHIL-LA",latLng));

        arrayList.add(new BandObj(R.drawable.koyi_k_utho,
                "KOYI K UTHO nace como tributo al piloto de la serie animada Mazinger Z,  ha editado tres placas discográficas: Mechanical Human Prototype (2004), VioLogic (2007) para la disquera EMI Music, Evi lution (2016), Cinco sencillos: E.V.A (2001), Koyi K utho (2002), Mechanical Animal, Fire on Fire (2011), Decode (2012)","Colombia",
                "Metal Industrial","Univalle","---- --/-- --:--",
                "KOYI K UTHO",latLng));

        arrayList.add(new BandObj(R.drawable.alerta_kamarada,
                "Fundada en 1996, Alerta Kamarada es la banda pionera del Reggae Colombiano. Nace en Bogotá, bajo la conducción de Pablo ¨one2¨ Araoz  y Javier ¨jr punk¨ Fonseca. Han tocado el Festivales mas importantes del género en el mundo y compartido tarima y canciones con los maximos exponentes del género. ","Colombia",
                "Reggae","Univalle","---- --/-- --:--",
                "Alerta Kamarada Sound System",latLng));

        arrayList.add(new BandObj(R.drawable.dr_krapula,
                "Doctor Krapula, es la banda de Rock colombiana mas influyente del movimiento artístico consciente de América latina, cumpliendo 20 años con 8 álbumes de estudio, 1 Dvd grabado, 5 nominaciones a los Latin grammy, 3 nominaciones a los mtv latino, condecorados por el congreso de la republica por su trabajo pro el ambiente, creadores del festival viva el planeta, una fiesta rebelde con un show explosivo","Colombia",
                "Rock","Univalle","---- --/-- --:--",
                "Doctor Krapula",latLng));




        arrayList.add(new BandObj(R.drawable.keko_yoma,
                "14 años de trayectoria, 5 discos, 2 Dvd y 17 giras internacionales por 18 países y varios festivales de primera línea como son Rock For Peolple (República Cheka), Quito Fest (Ecuador), Gente fest (Bélgica), Lollapalooza (Chile) entre muchos otros...hacen de KekoYoma una experiencia potente, latina y mágica.",
                "Chile",
                "Rock mestizo fiesta","Univalle","---- --/-- --:--",
                "KekoYoma",latLng));

        arrayList.add(new BandObj(R.drawable.chocloneta,
                "Banda del extremo austral del mundo con sonoridades etnicas chilenas y latinoamericanas que se mezcla con las sonoridades mas clasicas del rock y metal , con una lirica descarnada y cruda en castellano con toques ludicos .",
                "Chile",
                "Rock","Univalle","---- --/-- --:--",
                "CHOCLONETA ",latLng));

        arrayList.add(new BandObj(R.drawable.puerquerama,
                "Puerquerama es una banda de rock o algo parecido. Surgieron hace 18 años en Toluca, una fría y gris ciudad cercana a la Ciudad de México. Los puercos, como cariñosamente les dicen sus seguidores, poco a poco han logrado ganarse un lugar en la diversa escena musical independiente mexicana con su estilo crudo y desenfadado.",
                "Mexico",
                "Rock Undergrasoso","Univalle","---- --/-- --:--",
                "PUERQUERAMA",latLng));

        arrayList.add(new BandObj(R.drawable.valverde,
                "Valverde nace en la mitad de los Andes, Latacunga-Ecuador, su música fusiona ritmos como: Reggae, Ska, Dancehall, Ragamuffin, Dub, Rocksteady, han sido parte de la escena musical desde 2010 tocando en ciudades y en festivales de renombre en  Ecuador: como la Fiesta de la música, Cornetto fest, FFF Ambato, etc.",
                "Ecuador",
                "Reggae","Univalle","---- --/-- --:--",
                "VALVERDE",latLng));

        arrayList.add(new BandObj(R.drawable.entrelineas,
                "El género de su música es Pop-Rock electrónico con algunas influencias latinas. Después de participar en su primer Vive Latino en el 2018, la banda estará de Gira por México, Guatemala, Costa Rica y Colombia para Octubre de este año. ",
                "Costa Rica",
                "Rock Pop","Univalle","---- --/-- --:--",
                "Entrelineas",latLng));

        arrayList.add(new BandObj(R.drawable.we_are_wolves,
                "Con más de quince años de carrera es seguro decir que We Are Wolves es una banda con experiencia. A través de sus seis discos, han innovado dentro del art, dance, punk, pop. Su lanzamiento más reciente, WRONG, presenta un sonido más brillante, más pop y dramático para la banda, probando que estos lobos siguen aun de caza.  ",
                "Canada",
                "Punk/Dance/Pop","Univalle","---- --/-- --:--",
                "We are Wolves",latLng));*/

        return arrayList;

    }

    @Override
    public void onBackPressed(){

    }

}
