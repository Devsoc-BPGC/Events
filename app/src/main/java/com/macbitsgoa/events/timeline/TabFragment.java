package com.macbitsgoa.events.timeline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.events.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {

    private Tabs tabInfo;

    public static TabFragment getInstance (Tabs tabInfo) {
        TabFragment tabFragment = new TabFragment();
        tabFragment.tabInfo = tabInfo;
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_timeline, container, false);
    }
}
