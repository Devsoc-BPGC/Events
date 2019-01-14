package com.macbitsgoa.events.speakers;

import android.os.Bundle;
import android.content.Intent;
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

public class SpeakersActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private Browser browser;
    private ArrayList<Speakers> speakerList = new ArrayList<>(0); //ChangeMade
    public String desc,designation,imageUrl,name,onClickUrl;  //ChangeMade

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);

        browser = new Browser(this);

        final Toolbar toolbar = findViewById(R.id.speakers_toolbar);
        toolbar.setTitle(getString(R.string.speakers));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(SpeakersActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView=findViewById(R.id.speakersrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("speakers");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //speakersList.add(child.getValue(Speakers.class));
                    desc=child.child("desc").getValue(String.class); //ChangeStarted
                    designation=child.child("designation").getValue(String.class);
                    imageUrl=child.child("imageUrl").getValue(String.class);
                    name=child.child("name").getValue(String.class);
                    onClickUrl=child.child("onClickUrl").getValue(String.class);

                    Speakers speakersList=new Speakers(imageUrl,name,desc,designation,onClickUrl);
                    speakerList.add(speakersList);
                }

                adapter = new SpeakersAdapter(speakerList, browser, getApplicationContext()); //ChangeEnded
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ClassName","error"+ databaseError.toString());
            }
        });
    }
}