package com.macbitsgoa.events.aboutfest;

import android.os.Bundle;

import com.macbitsgoa.events.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class AboutFestActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_fest);

        ViewPager viewPager = (ViewPager)findViewById(R.id.about_fest_viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0: return LogoFragment.newInstance("This is logo");
                case 1: return DescriptionFragment.newInstance("This is description");
                case 2: return OrganisersFragment.newInstance("This is organiser");
                default: return LogoFragment.newInstance("This is default logo");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
