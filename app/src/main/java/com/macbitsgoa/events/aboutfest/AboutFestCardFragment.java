package com.macbitsgoa.events.aboutfest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

public class AboutFestCardFragment extends Fragment implements View.OnClickListener {
    private MaterialCardView selfCard;

    public AboutFestCardFragment() {

    }

    public static AboutFestCardFragment newInstance() {
        final AboutFestCardFragment fragment = new AboutFestCardFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selfCard = (MaterialCardView) inflater.inflate(R.layout.fragment_about_fest_card,
                container, false);
        return selfCard;
    }

    @Override
    public void onResume() {
        super.onResume();
        selfCard.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        selfCard.setOnClickListener(null);
        super.onPause();
    }

    @Override
    public void onClick(final View view) {
        final Intent aboutFestActivityIntent = new Intent(view.getContext(), AboutFestActivity.class);
        startActivity(aboutFestActivityIntent);
    }
}
