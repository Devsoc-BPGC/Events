package com.macbitsgoa.events.DosmEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.macbitsgoa.events.Authentication.SignupActivity;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.macbitsgoa.events.R;


public class ProfileAndLeaderboardActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentPagerAdapter fragmentPagerAdapter;
    FirebaseAuth auth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_leaderboard);
        viewPager=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tabs);
        fragmentPagerAdapter =new FragmentsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
       tabLayout.getTabAt(0).setText("Profile Details");
        tabLayout.getTabAt(1).setText("LeaderBoard");
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        if(currentUser==null)
        {
            startActivity(new Intent(ProfileAndLeaderboardActivity.this, SignupActivity.class));
            finish();
        }




    }

}
