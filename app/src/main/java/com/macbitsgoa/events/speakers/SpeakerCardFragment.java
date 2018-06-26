package com.macbitsgoa.events.speakers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeCardInterface;

import androidx.fragment.app.Fragment;

/*
 * Created by Hariram.R
 * 13/06/2018
 * */

public class SpeakerCardFragment extends Fragment implements View.OnClickListener,
        HomeCardInterface {

    private MaterialCardView cardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cardView = (MaterialCardView) inflater.inflate(R.layout.fragment_speakers_card,
                container, false);
        return cardView;
    }

    @Override
    public void onClick(View view) {
        Intent intentToSpeakersActivity = new Intent(view.getContext(), SpeakersActivity.class);
        startActivity(intentToSpeakersActivity);
    }

    @Override
    public void onResume() {
        super.onResume();
        cardView.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        cardView.setOnClickListener(null);
    }
}
