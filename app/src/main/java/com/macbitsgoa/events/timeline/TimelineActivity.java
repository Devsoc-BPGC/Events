package com.macbitsgoa.events.timeline;

import android.os.Bundle;

import com.macbitsgoa.events.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

/**
 * @author Rushikesh Jogdand.
 */
public class TimelineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        final TimelinePagerAdapter adapter
                = new TimelinePagerAdapter(getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager_timeline);
        viewPager.setAdapter(adapter);
    }
}
