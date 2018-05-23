package com.macbitsgoa.events.aboutfest;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

public class LogoFragment extends Fragment {
    private static final String TAG = LogoFragment.class.getSimpleName();
    private View view;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_fest_logo, container, false);

        //Database Initialization
        databaseReference = FirebaseDatabase.getInstance().getReference().child("aboutfest");

        getLogo();
        getTagline();
        getDates();

        return view;
    }

    /**
     * Creates the logo fragment in AboutFestActivity.
     */
    public static LogoFragment newInstance() {
        LogoFragment logoFragment = new LogoFragment();
        Bundle bundle = new Bundle();
        logoFragment.setArguments(bundle);

        return logoFragment;
    }

    private void getLogo() {
        databaseReference.child("logourl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String url = dataSnapshot.getValue(String.class);
                Uri imageUri = Uri.parse(url);
                SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.logo);
                draweeView.setImageURI(imageUri);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage(), databaseError.toException());
            }
        });
    }

    private void getTagline() {
        TextView tagline = (TextView) view.findViewById(R.id.tagline);
        databaseReference.child("tagline").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tagline.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage(), databaseError.toException());
            }
        });
    }

    private void getDates() {
        TextView dates = (TextView) view.findViewById(R.id.dates);
        databaseReference.child("dates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                dates.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage(), databaseError.toException());
            }
        });
    }
}
