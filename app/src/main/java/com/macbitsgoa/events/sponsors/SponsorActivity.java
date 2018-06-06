package com.macbitsgoa.events.sponsors;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SponsorActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<SponsorItem> sponsorItems;
    String sponsorName,sponsorClickUrl,sponsorLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("sponsors");
        sponsorItems=new ArrayList<>();
        recyclerView=findViewById(R.id.sponsorsrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    sponsorName= child.child("name").getValue(String.class);
                    sponsorLogo = child.child("logo").getValue(String.class);
                    sponsorClickUrl= child.child("clickUrl").getValue(String.class);

                    SponsorItem sponsorItem = new SponsorItem(sponsorName,sponsorLogo,sponsorClickUrl);
                    sponsorItems.add(sponsorItem);
                }

                adapter = new SponsorAdapter(sponsorItems,SponsorActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
