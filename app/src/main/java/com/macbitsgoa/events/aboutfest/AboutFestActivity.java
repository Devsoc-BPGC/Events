package com.macbitsgoa.events.aboutfest;

import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.tabs.TabLayout;
import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class AboutFestActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_fest);

        //Initialize Fresco
        Fresco.initialize(this);


        ViewPager viewPager = (ViewPager)findViewById(R.id.about_fest_viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return LogoFragment.newInstance();
                case 1: return DescriptionFragment.newInstance();
                case 2: return OrganisersFragment.newInstance();
                default: return LogoFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
