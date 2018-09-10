package com.macbitsgoa.events.timeline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.macbitsgoa.events.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


/**
 * Activity showing time line of events.
 * Use {@link #launchTimeLineActivity(Activity)} to launch this activity.
 *
 * @author Rushikesh Jogdand.
 */
public class TimelineActivity extends AppCompatActivity {

    public static void launchTimeLineActivity(Activity current) {
        Intent timelineIntent = new Intent(current, TimelineActivity.class);
        current.startActivity(timelineIntent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        setupToolbar();
        setupViewPager();
    }

    private void setupViewPager() {
        final TabLayout tabLayout = findViewById(R.id.activity_timeline_tabs);
        final ViewPager viewPager = findViewById(R.id.view_pager_timeline);
        final TimelinePagerAdapter pagerAdapter = new TimelinePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupToolbar() {
        final Toolbar toolbar = findViewById(R.id.activity_timeline_toolbar);
        toolbar.setTitle(getString(R.string.timeline));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));
    }
}

