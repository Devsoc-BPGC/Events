package com.macbitsgoa.events.timeline;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.macbitsgoa.events.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.ViewHolder} for a session.
 * @author Rushikesh Jogdand.
 */
public class SessionVh extends RecyclerView.ViewHolder {
    private final TextView titleTv;
    private final Chip durationChip;

    /**
     * Constructor.
     * @param itemView created view.
     */
    public SessionVh(@NonNull final View itemView) {
        super(itemView);
        titleTv = itemView.findViewById(R.id.tv_event_name);
        durationChip = itemView.findViewById(R.id.chip_duration);
    }

    /**
     * Pass the data.
     * @param session {@link Session}
     */
    public void populate(@NonNull final Session session) {
        titleTv.setText(String.format("[%s] %s", session.session, session.eventName));

        durationChip.setText(session.duration);
    }
}
