package com.macbitsgoa.events.timeline;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

/**
 * View model for {@link DayFragment}.
 * @author Rushikesh Jogdand.
 */
public class DayVm extends ViewModel {
    private List<String> startTimeList;

    /**
     * Initializes the view model with required parameters.
     *
     * @param date of the fragment in "dd,mm,yyyy" format
     */
    public void init(@NonNull final String date) {
        startTimeList = new ArrayList<>(0);
        startTimeList.add(date + ",10,00");
        startTimeList.add(date + ",11,00");
        startTimeList.add(date + ",12,00");
        startTimeList.add(date + ",13,00");
        startTimeList.add(date + ",14,00");
        startTimeList.add(date + ",15,00");
    }

    public List<String> getStartTimeList() {
        return startTimeList;
    }
}
