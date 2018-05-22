package com.macbitsgoa.events.aboutfest;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class LogoFragment extends Fragment {

    View v;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_fest_logo, container, false);

        //Database Initialization
        databaseReference = FirebaseDatabase.getInstance().getReference().child("aboutfest");

        getLogo();
        getTagline();
        getDates();

        return v;
    }

    public static LogoFragment newInstance() {
        LogoFragment logoFragment = new LogoFragment();
        Bundle bundle = new Bundle();
        logoFragment.setArguments(bundle);

        return logoFragment;
    }

    private void getLogo() {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView)v.findViewById(R.id.logo);
        TextView dates = (TextView) v.findViewById(R.id.dates);
        databaseReference.child("logourl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String url = dataSnapshot.getValue(String.class);
                Uri imageUri = Uri.parse(url);
                SimpleDraweeView draweeView = (SimpleDraweeView) v.findViewById(R.id.logo);
                draweeView.setImageURI(imageUri);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getTagline() {
        CardView cardView = (CardView) v.findViewById(R.id.cardview2);
        cardView.setCardElevation(1);
        TextView tagline = (TextView) v.findViewById(R.id.tagline);
        databaseReference.child("tagline").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tagline.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDates() {
        CardView cardView = (CardView) v.findViewById(R.id.cardview2);
        cardView.setCardElevation(1);
        TextView dates = (TextView) v.findViewById(R.id.dates);
        databaseReference.child("dates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                dates.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
