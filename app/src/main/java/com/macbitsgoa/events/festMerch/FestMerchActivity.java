package com.macbitsgoa.events.festMerch;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.Browser;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FestMerchActivity extends AppCompatActivity{

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private Browser browser;
    private ArrayList<Merchandise> merchandiseList = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festmerch);

        browser = new Browser(this);
        final Toolbar toolbar = findViewById(R.id.festmerch_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView=findViewById(R.id.festmerchrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("merch");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    merchandiseList.add(child.getValue(Merchandise.class));
                }

                adapter = new FestMerchAdapter(merchandiseList, browser);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ClassName","error"+ databaseError.toString());
            }
        });

    }
}
