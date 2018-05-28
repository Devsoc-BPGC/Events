package com.macbitsgoa.events.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.macbitsgoa.events.BuildConfig;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.aboutfest.AboutFestCardFragment;
import com.macbitsgoa.events.aboutmac.AboutMacCardFragment;
import com.macbitsgoa.events.eateries.EateriesCardFragment;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * Main Activity of the App.
 *
 * @author Rushikesh Jogdand [rushikeshjogdand1@gmail.com]
 */
@SuppressLint("GoogleAppIndexingApiWarning")
public class HomeActivity extends FragmentActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static boolean areFeaturesPopulated = false;

    private NavigationView navigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        if (!areFeaturesPopulated) {
            populateFeatures();
        }

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void initViews() {
        setContentView(R.layout.activity_home);
    }

    private void populateFeatures() {
        final FragmentManager featuresFragManager = getSupportFragmentManager();

        // example use of build config variable
        if (BuildConfig.eateries) {
            featuresFragManager
                    .beginTransaction()
                    .add(R.id.ll_home,
                            EateriesCardFragment.newInstance(),
                            getString(R.string.frag_label_eateries_card)
                    )
                    .commit();
        }

        if (BuildConfig.aboutFest) {
            featuresFragManager
                    .beginTransaction()
                    .add(R.id.ll_home,
                            AboutFestCardFragment.newInstance(),
                            getString(R.string.frag_label_aboutfest_card)
                    )
                    .commit();
        }

        if (BuildConfig.aboutMac) {
            featuresFragManager
                    .beginTransaction()
                    .add(R.id.ll_home,
                            AboutMacCardFragment.newInstance(),
                            getString(R.string.frag_label_aboutmac_card)
                    )
                    .commit();
        }
        areFeaturesPopulated = true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case (R.id.nav_share_app): {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Download this app from PlayStore");
                shareIntent.setType("text/plain");

                startActivity(Intent.createChooser(shareIntent, "Share app via: "));
                break;
            }


            default: {
                break;
            }
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
