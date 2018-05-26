package com.macbitsgoa.events.aboutmac;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.macbitsgoa.events.R;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class AboutMacActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_mac);

        ViewPager aboutMacViewPager = (ViewPager)findViewById(R.id.about_mac_viewpager);
        aboutMacViewPager.setAdapter(new AboutMacPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(aboutMacViewPager, true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorMac));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorMac));
        }
    }

}
