package com.macbitsgoa.events.aboutfest;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class DescriptionFragment extends Fragment {
    View v;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_fest_description, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("aboutfest");

        getDescription();
        return v;
    }

    public static DescriptionFragment newInstance() {
        DescriptionFragment descriptionFragment = new DescriptionFragment();
        Bundle bundle = new Bundle();
        descriptionFragment.setArguments(bundle);
        return descriptionFragment;
    }

    private void getDescription() {
        TextView description = (TextView) v.findViewById(R.id.description);
        databaseReference.child("description").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                description.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
