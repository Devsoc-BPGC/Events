package com.macbitsgoa.events.speakers;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;


import java.util.ArrayList;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
 * Created by Hariram.R
 * 13/06/2018
 * */

public class SpeakersActivity extends FragmentActivity {

    private ArrayList<Speaker> SpeakerList = new ArrayList<>();

    private static String TAG = SpeakersActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);


        RecyclerView speakersRV = findViewById(R.id.speakers_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        speakersRV.setLayoutManager(layoutManager);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("speakers");
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    Speaker speaker1 = new Speaker();
                    speaker1.Name = child.child("name").getValue(String.class);
                    speaker1.Desc = child.child("desc").getValue(String.class);
                    speaker1.imgURL = child.child("imageUrl").getValue(String.class);
                    speaker1.clickURL = child.child("onClickUrl").getValue(String.class);

                    SpeakerList.add(speaker1);
                }

                SpeakersAdapter adapter = new SpeakersAdapter(SpeakerList,SpeakersActivity.this);
                speakersRV.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,databaseError.getMessage());

            }
        });



    }
}
