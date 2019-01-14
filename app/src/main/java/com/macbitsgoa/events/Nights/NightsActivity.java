package com.macbitsgoa.events.Nights;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.Browser;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NightsActivity extends AppCompatActivity{

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private Browser browser;
    private ArrayList<Nights> NightList = new ArrayList<>(0); //ChangeMade
    public String Uid1,name,desc,imageURL,time,location;  //ChangeMade

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nights);

        browser = new Browser(this);

        final Toolbar toolbar = findViewById(R.id.nights_toolbar);
        toolbar.setTitle(getString(R.string.nights));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(NightsActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView=findViewById(R.id.nightsrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Nights");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //speakersList.add(child.getValue(Speakers.class));
                    desc=child.child("desc").getValue(String.class);
                    location=child.child("location").getValue(String.class);
                    imageURL=child.child("imageURL").getValue(String.class);
                    name=child.child("name").getValue(String.class);
                    time=child.child("time").getValue(String.class);
                    Uid1=child.child("Uid1").getValue(String.class);

                    Nights nightsList=new Nights(Uid1,name,desc,imageURL,time,location);
                    NightList.add(nightsList);
                }

                adapter = new NightsAdapter(NightList, browser, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ClassName","error"+ databaseError.toString());
            }
        });
    }
}
