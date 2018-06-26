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
    private ArrayList<String> nameList;
    private ArrayList<String> descList;
    private ArrayList<String> imgList;
    private ArrayList<String> clickList;

    private static String TAG = SpeakersActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);

        RecyclerView speakersRV = findViewById(R.id.speakers_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        speakersRV.setLayoutManager(layoutManager);

        nameList = new ArrayList<>();
        descList = new ArrayList<>();
        imgList = new ArrayList<>();
        clickList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference().child("speakers");
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    nameList.add(child.child("name").getValue(String.class));
                    descList.add(child.child("desc").getValue(String.class));
                    imgList.add(child.child("imageUrl").getValue(String.class));
                    clickList.add(child.child("onClickUrl").getValue(String.class));
                }
                SpeakersAdapter adapter = new SpeakersAdapter(nameList, descList, imgList,
                        clickList, SpeakersActivity.this);
                speakersRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());

            }
        });

    }
}