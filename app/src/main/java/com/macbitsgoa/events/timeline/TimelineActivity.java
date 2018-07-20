package com.macbitsgoa.events.timeline;

import android.os.Bundle;
import android.support.*;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.BuildConfig;
import com.macbitsgoa.events.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
//import androidx.viewpager.widget.ViewPager;


/**
 * Activity showing time line of events.
 * @author Rushikesh Jogdand.
 */
public class TimelineActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private String eventName;
    private String eventCategory;
    private String eventSession;
    private String eventVenue;
    private String eventTime;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ValueEventListener mEventRefListener;


    ArrayList<Tabs> tabInfo = new ArrayList<>();

    DatabaseReference databaseReference;
    //final int maxSize = 20;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        final Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle(getString(R.string.timeline));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));


        tabLayout = findViewById(R.id.activity_timeline_tabs);
        viewPager = findViewById(R.id.view_pager_timeline);

        tabInfo.clear();
        int days = BuildConfig.eventDuration;

        for (int i=0; i<=days; i++) {
            Tabs tab = new Tabs(i, "Day " + (i));

            tabInfo.add(tab);
        }

        setupViewPager();


    }

    public void setupViewPager () {
        final ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        TabFragment tabs[] = new TabFragment[tabInfo.size()];

        for (int i = 0; i < tabInfo.size(); i++) {
            tabs[i] = TabFragment.getInstance(tabInfo.get(i));
            pagerAdapter.addFragment(tabs[i], tabInfo.get(i).getTabName());
        }

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
