package com.macbitsgoa.events.aboutmac;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DevelopersFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private ArrayList<DevelopersList> developersList = new ArrayList<>();
    private static final String TAG = DevelopersFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_mac_developers, container, false);

        recyclerView = view.findViewById(R.id.developersRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new DevelopersAdapter(developersList, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("aboutmac");
        databaseReference.keepSynced(true);
        databaseReference.child("developers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                developersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DevelopersList item = snapshot.getValue(DevelopersList.class);
                    developersList.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage(), databaseError.toException());
            }
        });

        return view;
    }

    public static DevelopersFragment newInstance() {
        DevelopersFragment developersFragment = new DevelopersFragment();
        return developersFragment;
    }
}

