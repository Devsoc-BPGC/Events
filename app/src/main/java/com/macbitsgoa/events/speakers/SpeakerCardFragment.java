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

public class SpeakerCardFragment extends Fragment implements HomeCardInterface{


    public static SpeakerCardFragment newInstance()
    {
        SpeakerCardFragment fragment = new SpeakerCardFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.fragment_speakers_card,
                container, false);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchSpeakersIntent = new Intent(view.getContext(),SpeakersActivity.class);
                startActivity(launchSpeakersIntent);

            }
        });
        return cardView;
    }

}
