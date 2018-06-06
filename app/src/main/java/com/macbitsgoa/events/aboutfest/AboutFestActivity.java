package com.macbitsgoa.events.aboutfest;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.macbitsgoa.events.R;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class AboutFestActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_fest);

        ViewPager viewPager = (ViewPager) findViewById(R.id.about_fest_viewpager);
        viewPager.setAdapter(new AboutFestPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);
    }


}
