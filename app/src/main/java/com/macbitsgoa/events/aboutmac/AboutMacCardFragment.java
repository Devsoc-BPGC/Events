package com.macbitsgoa.events.aboutmac;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeCardInterface;

import androidx.fragment.app.Fragment;

public class AboutMacCardFragment extends Fragment
        implements View.OnClickListener, HomeCardInterface {
    private MaterialCardView selfCard;

    public AboutMacCardFragment() {

    }

    /**
     * Card for AboutMac on HomeActivity.
     */
    public static AboutMacCardFragment newInstance() {
        final AboutMacCardFragment fragment = new AboutMacCardFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selfCard = (MaterialCardView) inflater.inflate(R.layout.fragment_about_mac_card,
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
        final Intent aboutMacActivityIntent = new Intent(view.getContext(),
                AboutMacActivity.class);
        startActivity(aboutMacActivityIntent);
    }
}
