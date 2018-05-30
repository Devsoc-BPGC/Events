package com.macbitsgoa.events.timeline;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

/**
 * @author Rushikesh Jogdand.
 */
public class DayVm extends ViewModel {
    private List<String> startTimeList;

    public void init(@NonNull final String date) {
        final String date1 = date;
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
