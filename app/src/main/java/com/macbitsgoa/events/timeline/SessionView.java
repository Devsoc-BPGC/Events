package com.macbitsgoa.events.timeline;

import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;

import com.google.android.material.chip.Chip;
import com.macbitsgoa.events.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Views of timeline.
 * Layout : {@link com.macbitsgoa.events.R.layout#vh_session}
 * TODO: add more elements to layout and populate them. Use {@link #populate(Session)}
 *
 * @author Rushikesh Jogdand.
 */
public class SessionView extends RecyclerView.ViewHolder {

    private final TextView eventNameTv;
    private final Chip timeChip;
    private final SimpleDraweeView bgSdv;

    public SessionView(final View itemView) {
        super(itemView);
        eventNameTv = itemView.findViewById(R.id.tv_event_name);
        timeChip = itemView.findViewById(R.id.chip_time);
        bgSdv = itemView.findViewById(R.id.sdv_bg);
    }

    public void populate(Session session) {
        eventNameTv.setText(session.name);
        timeChip.setText(session.time);
        bgSdv.setImageURI(session.imageUrl);
    }
}
