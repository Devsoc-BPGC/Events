package com.macbitsgoa.events.LeaderBoard;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.events.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment {
    RecyclerView recyclerView;
    LeaderBoardAdapter adapter;
    String name[]={"kartik","ritik","rohit","raju","Raj"};
    Integer rank[]={1,2,3,4,5};
    Integer points[]={100,90,80,70,60};
    ArrayList<LeaderBoardModel> dummydata =new ArrayList<>(5);




    public LeaderBoardFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.leaderboard_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        for(int i=0;i<5;i++)
        {
            dummydata.add(i,new LeaderBoardModel(name[i],points[i],rank[i]));
        }
        adapter =new LeaderBoardAdapter(dummydata);
        recyclerView.setAdapter(adapter);

    }
}
