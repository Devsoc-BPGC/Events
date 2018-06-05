package com.macbitsgoa.events.aboutmac;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AboutMacPagerAdapter extends FragmentPagerAdapter {

    public AboutMacPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return MacLogoFragment.newInstance();
            case 1: return DevelopersFragment.newInstance();
            case 2: return MacConnectFragment.newInstance();
            default: return MacLogoFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
