package com.macbitsgoa.events.aboutmac;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.eateries.EateriesActivity;
import com.macbitsgoa.events.home.HomeActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class AboutMacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_mac);
        final Toolbar toolbar = findViewById(R.id.mac_toolbar);
        toolbar.setTitle(getString(R.string.about_mac));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(AboutMacActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ViewPager aboutMacViewPager = (ViewPager) findViewById(R.id.about_mac_viewpager);
        aboutMacViewPager.setAdapter(new AboutMacPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(aboutMacViewPager, true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorMac));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorMac));
        }
    }

}
