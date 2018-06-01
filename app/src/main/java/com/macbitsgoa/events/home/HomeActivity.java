package com.macbitsgoa.events.home;

import android.annotation.SuppressLint;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.macbitsgoa.events.BuildConfig;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.aboutfest.AboutFestCardFragment;
import com.macbitsgoa.events.aboutmac.AboutMacCardFragment;
import com.macbitsgoa.events.eateries.EateriesCardFragment;
import com.macbitsgoa.events.timeline.TimelineCardFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import static com.macbitsgoa.events.Events.playStoreLink;
import static com.macbitsgoa.events.Utilities.MIME_TYPE_PLAINTEXT;

/**
 * Main Activity of the App.
 *
 * @author Rushikesh Jogdand [rushikeshjogdand1@gmail.com]
 */
@SuppressLint("GoogleAppIndexingApiWarning")
public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static boolean areFeaturesPopulated = false;

    private DrawerLayout drawerLayout;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        initViews();
        if (!areFeaturesPopulated) {
            populateFeatures();
        }

        drawerLayout = findViewById(R.id.drawer_layout);

        final NavigationView navigationView;
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

    }

    private void initViews() {
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer_layout);
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

        if (BuildConfig.timeline) {
            featuresFragManager
                    .beginTransaction()
                    .add(R.id.ll_home,
                            new TimelineCardFragment(),
                            getString(R.string.timeline)
                    )
                    .commit();
        }

        areFeaturesPopulated = true;
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

            default: {
                break;
            }
        }

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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

}
