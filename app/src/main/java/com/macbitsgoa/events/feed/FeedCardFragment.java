package com.macbitsgoa.events.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeCardInterface;

import androidx.fragment.app.Fragment;

public class FeedCardFragment extends Fragment
        implements HomeCardInterface {

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        final MaterialCardView cardView =
                (MaterialCardView) inflater.inflate(R.layout.fragment_feed,
                        container, false);
        cardView.setOnClickListener(view ->
                startActivity(new Intent(view.getContext(), FeedActivity.class)));
        return cardView;
    }
}
