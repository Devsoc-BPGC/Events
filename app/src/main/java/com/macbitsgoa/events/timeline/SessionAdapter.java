package com.macbitsgoa.events.timeline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.events.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Standard adapter.
 *
 * @author Rushikesh Jogdand.
 */
public class SessionAdapter extends RecyclerView.Adapter<SessionView> {
    private List<Session> sessionList;

    public SessionAdapter(final List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public void setSessionList(final List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    @Override
    public SessionView onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_session, parent, false);
        return new SessionView(v);
    }

    @Override
    public void onBindViewHolder(final SessionView holder, final int position) {
        holder.populate(sessionList.get(position));
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }
}
