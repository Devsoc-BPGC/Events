package com.macbitsgoa.events.LeaderBoard;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment {
    RecyclerView recyclerView;
    private DatabaseReference db;
    public RecyclerView.Adapter adapter;

    ArrayList<LeaderBoardModel> leaderBoardData =new ArrayList<>(0);






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
        db=FirebaseDatabase.getInstance().getReference().child("DosmEvent").child("Users").orderByChild("totalpoints").getRef();
        loadData();



    }

    private void loadData() {

        db.orderByChild("totalpoints").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    leaderBoardData.add(child.getValue(LeaderBoardModel.class));

                }

                adapter = new LeaderBoardAdapter(leaderBoardData);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ClassName","error"+ databaseError.toString());
            }
        });
    }

    public void onStart() {


        super.onStart();
    }

}
