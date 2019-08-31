package com.macbitsgoa.events.LeaderBoard;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.macbitsgoa.events.R;
import java.lang.*;


public class LeaderboardViewholder extends RecyclerView.ViewHolder {
    TextView rank;
    private TextView name;
    private TextView points;

    public LeaderboardViewholder(@NonNull View itemView) {
        super(itemView);
        rank=itemView.findViewById(R.id.rank);
        name=itemView.findViewById(R.id.player_name);
        points=itemView.findViewById(R.id.points);

    }
    public  void populate(LeaderBoardModel item)
    {
        String Rank=String.valueOf(getAdapterPosition()+1);
        rank.setText(Rank);
        name.setText(item.getName());
        points.setText(String.valueOf(item.getTotalpoints()));
    }
}
