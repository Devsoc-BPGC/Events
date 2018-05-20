package com.macbitsgoa.events.aboutfest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

public class OrganisersFragment extends Fragment {
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_fest_organisers, container, false);

        TextView tv = (TextView) v.findViewById(R.id.test_org);
        tv.setText(getArguments().getString("text_org"));
        return v;
    }

    public static OrganisersFragment newInstance(String text) {
        OrganisersFragment organisersFragment = new OrganisersFragment();
        Bundle bundle = new Bundle();

        bundle.putString("text_org", text);
        organisersFragment.setArguments(bundle);

        return organisersFragment;
    }
}
