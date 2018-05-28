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

    NavigationView navigationView;

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

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        // Start all your intent for activities in here

        switch (menuItem.getItemId()) {
            case (R.id.nav_registration): {
                // finish me
                break;
            }

            case (R.id.nav_events): {
                // finish me
                break;
            }

            case (R.id.nav_timeline): {
                // finish me
                break;
            }

            case (R.id.nav_speakers): {
                // finish me
                break;
            }

            case (R.id.nav_maps): {
                // finish me
                break;
            }

            case (R.id.nav_about_event): {
                // finish me
                break;
            }

            case (R.id.nav_about_developers): {
                // finish me
                break;
            }

            case (R.id.nav_share): {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT, "Download this app from PlayStore");
                intent1.setType("text/plain");

                startActivity(Intent.createChooser(intent1, "Share app via: "));
                break;
            }

            case (R.id.nav_social): {
                // finish me
                break;
            }
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
