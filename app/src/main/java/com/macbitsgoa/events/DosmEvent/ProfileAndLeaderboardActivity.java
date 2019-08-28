package com.macbitsgoa.events.DosmEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.macbitsgoa.events.R;


public class ProfileAndLeaderboardActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentPagerAdapter fragmentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_leaderboard);
        viewPager=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tabs);
        fragmentPagerAdapter =new FragmentsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
       tabLayout.getTabAt(0).setText("Profile Deatails");
        tabLayout.getTabAt(1).setText("LeaderBoard");



    }
}
