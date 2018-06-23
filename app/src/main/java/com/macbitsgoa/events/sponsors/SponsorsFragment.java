package com.macbitsgoa.events.sponsors;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeCardInterface;

public class SponsorsFragment extends androidx.fragment.app.Fragment
        implements HomeCardInterface {

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {
        final MaterialCardView selfCard =
                (MaterialCardView) inflater.inflate(R.layout.fragment_sponsors,
                        container, false);
        selfCard.setOnClickListener(view ->
                startActivity(new Intent(view.getContext(), SponsorActivity.class)));
        return selfCard;
    }
}
