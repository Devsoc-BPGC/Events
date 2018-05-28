package com.macbitsgoa.events.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

/**
 * Use the {@link feedCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class feedCardFragment extends Fragment implements View.OnClickListener {

    private MaterialCardView cardView;

    private feedCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment feedCardFragment.
     */
    public static feedCardFragment newInstance() {
        final feedCardFragment fragment = new feedCardFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cardView = (MaterialCardView) inflater.inflate(R.layout.fragment_feed, container, false);
        return cardView;
    }

    @Override
    public void onResume() {
        super.onResume();
        cardView.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        cardView.setOnClickListener(null);
        super.onPause();
    }

    @Override
    public void onClick(final View view) {
        final Intent feedActivityIntent = new Intent(view.getContext(), feedActivity.class);
        startActivity(feedActivityIntent);
    }
}
