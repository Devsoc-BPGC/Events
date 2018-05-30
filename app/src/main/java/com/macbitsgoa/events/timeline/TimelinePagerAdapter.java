package com.macbitsgoa.events.timeline;

import android.util.Log;

import com.macbitsgoa.events.BuildConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import static com.macbitsgoa.events.Utilities.HOURS_IN_DAY;
import static com.macbitsgoa.events.Utilities.SEC_IN_HR;
import static com.macbitsgoa.events.Utilities.TAG_PREFIX;

/**
 * {@link FragmentStatePagerAdapter} for "tab" fragments of timeline activity.
 * @author Rushikesh Jogdand.
 */
public class TimelinePagerAdapter extends FragmentStatePagerAdapter {

    @SuppressWarnings("StringConcatenationMissingWhitespace")
    private static final String TAG = TAG_PREFIX + TimelinePagerAdapter.class.getSimpleName();

    TimelinePagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        final String date = getDateForPosition(position);
        return date == null
                ? new DayFragment()
                : DayFragment.getInstance(date);
    }

    private static String getDateForPosition(final int day) {
        final SimpleDateFormat sdf
                = new SimpleDateFormat(BuildConfig.startDateFormat, Locale.US);
        final Date date;
        try {
            date = sdf.parse(BuildConfig.startDate);
        } catch (final ParseException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
            return null;
        }
        date.setTime(date.getTime() + day * HOURS_IN_DAY * SEC_IN_HR * 1000);
        return sdf.format(date);
    }

    @Override
    public int getCount() {
        return BuildConfig.eventDuration;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        final String rawDate = getDateForPosition(position);
        final SimpleDateFormat rawSdf = new SimpleDateFormat(BuildConfig.startDateFormat, Locale.US);
        final Date date;
        try {
            date = rawSdf.parse(rawDate);
        } catch (final ParseException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
            return ":(";
        }
        final SimpleDateFormat titleFormat
                = new SimpleDateFormat(BuildConfig.timelineTabDateFormat, Locale.getDefault());
        return titleFormat.format(date);
    }
}
