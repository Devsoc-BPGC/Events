package com.macbitsgoa.events.timeline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macbitsgoa.events.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author Rushikesh Jogdand.
 */
public class DayFragment extends Fragment {

    private static final String ARG_DATE = "date";

    public DayFragment() {
    }

    static DayFragment getInstance(@NonNull final String date) {
        final DayFragment fragment = new DayFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_day, container, false);
        final String date = getArguments().getString(ARG_DATE);
        final TextView placeholderTv = rootView.findViewById(R.id.tv_fragment_day_placeholder);
        placeholderTv.setText(date);
        return rootView;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
