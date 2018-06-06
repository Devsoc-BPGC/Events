package com.macbitsgoa.events.aboutfest;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AboutFestPagerAdapter extends FragmentPagerAdapter {

    public AboutFestPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LogoFragment.newInstance();
            case 1:
                return DescriptionFragment.newInstance();
            case 2:
                return OrganisersFragment.newInstance();
            default:
                return LogoFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
