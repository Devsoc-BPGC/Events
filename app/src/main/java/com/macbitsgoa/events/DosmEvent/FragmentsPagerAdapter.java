package com.macbitsgoa.events.DosmEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.macbitsgoa.events.LeaderBoard.LeaderBoardFragment;

public class FragmentsPagerAdapter extends FragmentPagerAdapter {

    public FragmentsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0) {return new ProfileFragment();}
        if(position==1){return new LeaderBoardFragment();}
        else return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
