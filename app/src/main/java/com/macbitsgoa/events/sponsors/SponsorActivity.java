package com.macbitsgoa.events.sponsors;

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
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SponsorActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public List<SponsorItem> sponsorItems;
    public String sponsorName,sponsorClickUrl,sponsorLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("sponsors");
        sponsorItems=new ArrayList<>();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sponsor);

        final Toolbar toolbar = findViewById(R.id.sponsor_toolbar);
        toolbar.setTitle(getString(R.string.sponsors));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(SponsorActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


        recyclerView=findViewById(R.id.sponsorsrecyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(mLayoutManager);
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
                Log.e("ClassName","error"+ databaseError.toString());
            }
        });
    }


}
