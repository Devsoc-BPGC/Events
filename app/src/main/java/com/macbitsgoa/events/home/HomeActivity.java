package com.macbitsgoa.events.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.macbitsgoa.events.BuildConfig;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.eateries.EateriesCardFragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * Main Activity of the App.
 *
 * @author Rushikesh Jogdand [rushikeshjogdand1@gmail.com]
 */
@SuppressLint("GoogleAppIndexingApiWarning")
public class HomeActivity extends FragmentActivity {

    private static boolean areFeaturesPopulated = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        if (!areFeaturesPopulated) {
            populateFeatures();
        }
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

        areFeaturesPopulated = true;
    }
}
