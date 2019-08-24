package com.macbitsgoa.events.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.macbitsgoa.events.BuildConfig;
import com.macbitsgoa.events.DosmEvent.ProfileAndLeaderboardActivity;
import com.macbitsgoa.events.Nights.NightsActivity;
import com.macbitsgoa.events.Nights.NightsFragment;
import com.macbitsgoa.events.QrScaner.QrScannerActivity;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.SocialActivity;
import com.macbitsgoa.events.aboutfest.AboutEventActivity;
import com.macbitsgoa.events.aboutmac.AboutMacActivity;
import com.macbitsgoa.events.eateries.EateriesCardFragment;
import com.macbitsgoa.events.speakers.SpeakersActivity;
import com.macbitsgoa.events.speakers.SpeakersFragment;
import com.macbitsgoa.events.sponsors.SponsorsFragment;
import com.macbitsgoa.events.timeline.TimelineCardFragment;
import com.macbitsgoa.events.timer.TimerCardFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.macbitsgoa.events.Events.playStoreLink;
import static com.macbitsgoa.events.Utilities.EVENTS;
import static com.macbitsgoa.events.Utilities.MIME_TYPE_PLAINTEXT;
import static com.macbitsgoa.events.timeline.TimelineActivity.launchTimeLineActivity;

/**
 * Main Activity of the App.
 *
 * @author Rushikesh Jogdand [rushikeshjogdand1@gmail.com]
 */
@SuppressLint("GoogleAppIndexingApiWarning")
public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private int code=1001;
  final   int My_Camera_Request_Code=102;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFeatures();
    }

    private void populateFeatures() {
        final FragmentManager featuresFragManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = featuresFragManager.beginTransaction();
        for (final Fragment fragment : featuresFragManager.getFragments()) {
            if (fragment instanceof HomeCardInterface) {
                fragmentTransaction.remove(fragment);
            }
        }
        // example use of build config variable

        if(BuildConfig.timer) {
            fragmentTransaction.add(R.id.ll_home,
                    new TimerCardFragment(),
                    "timer"
            );
        }

        if (BuildConfig.speakers) {
            fragmentTransaction.add(R.id.ll_home,
                    new SpeakersFragment(),
                    "speakers"
            );
        }


        if (BuildConfig.nights) {
            fragmentTransaction.add(R.id.ll_home,
                    new NightsFragment(),
                    "nights"
            );
        }



        /*if (BuildConfig.feed) {
            fragmentTransaction.add(R.id.ll_home,
                    new FeedCardFragment(),
                    "feed"
            );
        }*/

        if (BuildConfig.festmerch){
            fragmentTransaction.add(R.id.ll_home,
                    new com.macbitsgoa.events.festmerch.FestMerchFragment(),
                    "fest merchandise");
        }

        if (BuildConfig.eateries) {
            fragmentTransaction.add(R.id.ll_home,
                    new EateriesCardFragment(),
                    "exhibition"
            );
        }
        if (BuildConfig.timeline) {
            fragmentTransaction.add(R.id.ll_home,
                    new TimelineCardFragment(),
                    getString(R.string.timeline)
            );
        }
        /*if (BuildConfig.shouldShowMapOnHome) {
            fragmentTransaction.add(R.id.ll_home,
                    new MapCardFragment(),
                    "map");
        }*/

        if (BuildConfig.sponsors){
            fragmentTransaction.add(R.id.ll_home,
                    new SponsorsFragment(),
                    "sponsors");
        }


        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        initViews();

        drawerLayout = findViewById(R.id.drawer_layout);


        final NavigationView navigationView;
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        if(ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(HomeActivity.this,"permission not granted",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(HomeActivity.this,new String[]{Manifest.permission.CAMERA},My_Camera_Request_Code);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case My_Camera_Request_Code:{
                if(grantResults.length>0 &&grantResults[0] ==PackageManager.PERMISSION_GRANTED){

                } else {

                }
            }
        }


    }

    private void initViews() {
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer_layout);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case (R.id.nav_share_app): {
                final Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        getString(R.string.spam_text) + " " + playStoreLink);

                shareIntent.setType(MIME_TYPE_PLAINTEXT);
                startActivity(Intent.createChooser(shareIntent,
                        getString(R.string.choose_share_medium_prompt)));
                break;
            }

            /*case (R.id.nav_maps): {
                final Intent mapIntent = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(mapIntent);
                break;

            }*/

            case (R.id.nav_registration): {
                final CustomTabsIntent intent=new CustomTabsIntent.Builder().build();
                intent.launchUrl(HomeActivity.this, Uri.parse(EVENTS));
                //Uri uri = Uri.parse(EVENTS);
                //startActivity(new Intent(Intent.ACTION_VIEW, uri));
                break;
            }

            case (R.id.nav_nights): {
                startActivity(new Intent(HomeActivity.this, NightsActivity.class));
                break;

            }

            case (R.id.nav_about_event): {
                startActivity(new Intent(HomeActivity.this, AboutEventActivity.class));
                break;
            }

            case (R.id.nav_about_developers): {
                startActivity(new Intent(HomeActivity.this, AboutMacActivity.class));
                break;
            }

            case (R.id.nav_timeline): {
                launchTimeLineActivity(this);
                break;
            }
            case (R.id.nav_speakers): {
                startActivity(new Intent(HomeActivity.this, SpeakersActivity.class));
                break;
            }
            case (R.id.nav_social): {
                startActivity(new Intent(HomeActivity.this, SocialActivity.class));
                break;
            }
            case R.id.qr_scanner :{
                startActivityForResult(new Intent(HomeActivity.this,QrScannerActivity.class),code);
                break;
            }
            case R.id.user_details:{
                startActivity(new Intent(HomeActivity.this, ProfileAndLeaderboardActivity.class));


            }


            default: {
                break;
            }
        }

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case (android.R.id.home): {
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }


            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK&&requestCode==code){

            String code =data.getStringExtra("qr_result");
            Toast.makeText(HomeActivity.this,"result is "+code,Toast.LENGTH_LONG).show();
        }
    }
}
