package com.macbitsgoa.events.eateries;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

/**
 * The fragment for feature eateries to be shown on home screen
 * {@link com.macbitsgoa.events.home.HomeActivity}.
 */
public class EateriesCardFragment extends Fragment implements View.OnClickListener {

    private MaterialCardView selfCard;

    @SuppressWarnings("WeakerAccess")
    public EateriesCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment EateriesCardFragment.
     */
    public static EateriesCardFragment newInstance() {
        final EateriesCardFragment fragment = new EateriesCardFragment();
        final Bundle               args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selfCard = (MaterialCardView) inflater.inflate(R.layout.fragment_eateries_card,
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
        final Intent eateriesActivityIntent = new Intent(view.getContext(), EateriesActivity.class);
        startActivity(eateriesActivityIntent);
    }
}
