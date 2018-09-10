package com.macbitsgoa.events.timeline;

import android.app.Application;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;


/**
 * Data for a single day tab in timeline activity.
 * Use this via {@link #getVmForDay(int, Fragment)}.
 * @author Rushikesh Jogdand.
 */
public class DayVm extends AndroidViewModel {
    public LiveData<List<Session>> sessions;

    public DayVm(final Application application, final int day) {
        super(application);
        final TimelineRepo repo = new TimelineRepo(application);
        sessions = repo.getSessionsOfDay(day);
    }

    public static DayVm getVmForDay(int day, Fragment fragment) {
        return ViewModelProviders.of(fragment, new DayVmFactory(fragment.getActivity().getApplication(), day)).get(DayVm.class);
    }
}

