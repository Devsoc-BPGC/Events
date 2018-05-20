package com.macbitsgoa.events.aboutfest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

public class LogoFragment extends Fragment {

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_fest_logo, container, false);
        TextView tv = (TextView) v.findViewById(R.id.test_logo);
        tv.setText(getArguments().getString("text"));
        return v;
    }

    public static LogoFragment newInstance(String text) {
        LogoFragment logoFragment = new LogoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        logoFragment.setArguments(bundle);

        return logoFragment;
    }

}
