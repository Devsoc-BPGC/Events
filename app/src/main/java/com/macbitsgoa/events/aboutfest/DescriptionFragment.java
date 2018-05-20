package com.macbitsgoa.events.aboutfest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

public class DescriptionFragment extends Fragment {
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_fest_description, container, false);

        TextView tv1 = (TextView)v.findViewById(R.id.test_desc);
        tv1.setText(getArguments().getString("text_desc"));
        return v;
    }

    public static DescriptionFragment newInstance(String text) {
        DescriptionFragment descriptionFragment = new DescriptionFragment();
        Bundle bundle = new Bundle();

        bundle.putString("text_desc", text);
        descriptionFragment.setArguments(bundle);
        return descriptionFragment;
    }
}
