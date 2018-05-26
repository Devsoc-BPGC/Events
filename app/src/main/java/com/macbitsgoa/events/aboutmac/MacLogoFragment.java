package com.macbitsgoa.events.aboutmac;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

public class MacLogoFragment extends Fragment {
    private static final String TAG = MacLogoFragment.class.getSimpleName();
    private View view;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_mac_logo, container, false);

        //Database Initialization
        databaseReference = FirebaseDatabase.getInstance().getReference().child("aboutmac");

        getLogo();

        return view;
    }

    /**
     * Creates the logo fragment in AboutFestActivity.
     */
    public static MacLogoFragment newInstance() {
        MacLogoFragment logoFragment = new MacLogoFragment();
        Bundle bundle = new Bundle();
        logoFragment.setArguments(bundle);

        return logoFragment;
    }

    private void getLogo() {
        databaseReference.child("logo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String url = dataSnapshot.getValue(String.class);
                Uri imageUri = Uri.parse(url);
                SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.maclogo);
                draweeView.setImageURI(imageUri);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage(), databaseError.toException());
            }
        });
    }

}