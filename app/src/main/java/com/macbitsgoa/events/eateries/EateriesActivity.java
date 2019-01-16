package com.macbitsgoa.events.eateries;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeActivity;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EateriesActivity extends AppCompatActivity {

    private ArrayList<EateriesList> eateriesList = new ArrayList<>();
    private static final String TAG = EateriesActivity.class.getSimpleName();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.Adapter adapter;
        RecyclerView recyclerView;
        setContentView(R.layout.activity_eateries);
        final Toolbar toolbar = findViewById(R.id.eateries_toolbar);
        toolbar.setTitle(getString(R.string.eateries));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(EateriesActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
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
