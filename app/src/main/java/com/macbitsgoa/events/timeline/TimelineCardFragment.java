package com.macbitsgoa.events.timeline;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

/**
 * Card representing timeline feature in home.
 */
public class TimelineCardFragment extends Fragment implements View.OnClickListener {

    public TimelineCardFragment() {
    }


    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View selfView = inflater.inflate(R.layout.fragment_timeline_card, container, false);
        selfView.setOnClickListener(this);
        return selfView;
    }

    @Override
    public void onClick(final View view) {
        final Intent timelineActivityIntent;
        timelineActivityIntent = new Intent(getContext(), TimelineActivity.class);
        startActivity(timelineActivityIntent);
    }
}
