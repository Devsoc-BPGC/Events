package com.macbitsgoa.events.aboutfest;

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

public class OrganisersFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private ArrayList<OrganisersList> organisersList = new ArrayList<>();
    private static final String TAG = OrganisersFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_fest_organisers, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.organisersRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new OrganisersAdapter(organisersList, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("aboutfest");
        databaseReference.keepSynced(true);
        databaseReference.child("organisers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                organisersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrganisersList item = snapshot.getValue(OrganisersList.class);
                    System.out.println(item);
                    organisersList.add(item);
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

    public static OrganisersFragment newInstance() {
        OrganisersFragment organisersFragment = new OrganisersFragment();
        return organisersFragment;
    }
}
