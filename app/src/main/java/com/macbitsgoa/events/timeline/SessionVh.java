package com.macbitsgoa.events.timeline;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.macbitsgoa.events.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Rushikesh Jogdand.
 */
public class SessionVh extends RecyclerView.ViewHolder {
    private TextView titleTv;
    private Chip durationChip;

    public SessionVh(@NonNull final View itemView) {
        super(itemView);
        titleTv = itemView.findViewById(R.id.tv_event_name);
        durationChip = itemView.findViewById(R.id.chip_duration);
    }

    public void populate(@NonNull final Session session) {
        titleTv.setText(String.format("[%s] %s", session.session, session.eventName));

        durationChip.setText(session.duration);
    }
}
