package com.macbitsgoa.events.timeline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.events.Utilities.TAG_PREFIX;

/**
 * One tab of timeline activity.
 * Use {@link #getInstance(String, int)} to use this class.
 * @author Rushikesh Jogdand.
 */
public class DayFragment extends Fragment {

    private static final String ARG_DATE = "date";
    private static final String ARG_DAY = "day";
    private SessionAdapter sessionAdapter;

    protected static DayFragment getInstance(@NonNull final String date, int day) {
        final DayFragment fragment = new DayFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_DATE, date);
        args.putInt(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_day, container,
                false);
        final RecyclerView recyclerView = rootView.findViewById(R.id.rv_day);
        sessionAdapter = new SessionAdapter(new ArrayList<>());
        recyclerView.setAdapter(sessionAdapter);

        final int day = getArguments().getInt(ARG_DAY);
        final DayVm viewModel = DayVm.getVmForDay(day, this);
        viewModel.sessions.observe(this, sessions -> {
            sessionAdapter.setSessionList(sessions);
            sessionAdapter.notifyDataSetChanged();
        });

        return rootView;
    }
}
