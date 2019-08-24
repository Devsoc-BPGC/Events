package com.macbitsgoa.events.LeaderBoard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.macbitsgoa.events.R;

import java.util.ArrayList;


public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderboardViewholder> {
    ArrayList<LeaderBoardModel>data;

    @NonNull
    @Override
    public LeaderboardViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.leaderboard_rv_view,parent,false);
        LeaderboardViewholder holder = new LeaderboardViewholder(view);
        return holder;
    }

    public LeaderBoardAdapter(ArrayList<LeaderBoardModel> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewholder holder, int position) {
        holder.populate(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
