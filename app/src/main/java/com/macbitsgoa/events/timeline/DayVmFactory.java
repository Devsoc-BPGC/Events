package com.macbitsgoa.events.timeline;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Because we want to pass "day" argument to {@link DayVm}.
 * Can add other arguments if needed.
 */
public class DayVmFactory extends ViewModelProvider.NewInstanceFactory {
    private final int day;
    private Application app;

    public DayVmFactory(final Application application, final int day) {
        this.day = day;
        app = application;
    }

    @Override
    public <T extends ViewModel> T create(final Class<T> modelClass) {
        return (T) new DayVm(app, day);
    }
}
