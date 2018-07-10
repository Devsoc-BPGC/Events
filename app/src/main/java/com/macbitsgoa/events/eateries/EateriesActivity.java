package com.macbitsgoa.events.eateries;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EateriesActivity extends Activity {

    private ArrayList<EateriesList> eateriesList = new ArrayList<>();
    private static final String TAG = EateriesActivity.class.getSimpleName();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.Adapter adapter;
        RecyclerView recyclerView;

        setContentView(R.layout.activity_eateries);

        recyclerView = (RecyclerView)findViewById(R.id.eateriesRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new EateriesAdapter(eateriesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("eateries");
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eateriesList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    EateriesList item = snapshot.getValue(EateriesList.class);
                    eateriesList.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage(), databaseError.toException());
            }
        });
    }
}
