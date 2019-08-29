package com.macbitsgoa.events.ProfileDetails;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.DosmEvent.UserDetailsModal;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    public RecyclerView recyclerView;
    public View v;
    public RecyclerView.Adapter adapter;
    private ArrayList<ProfileModal> qrcodeList = new ArrayList<>(0);
    UserDetailsModal user =new UserDetailsModal();
    TextView username;
    TextView totalPoints;

    TextDrawable drawable;
    de.hdodenhof.circleimageview.CircleImageView profilePic;
    FirebaseDatabase database;
    DatabaseReference userQrcodelistRef;
    FirebaseAuth auth;
    FirebaseUser currentuser;
    DatabaseReference userDataRef;

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
       auth=FirebaseAuth.getInstance();

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        currentuser=auth.getCurrentUser();

        recyclerView = view.findViewById(R.id.qrcoderecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profilePic=view.findViewById(R.id.profile_pic);
        username=view.findViewById(R.id.username);
        totalPoints=view.findViewById(R.id.points);

        adapter = new ProfileFragmentAdapter(qrcodeList); //ChangeEnded
        recyclerView.setAdapter(adapter);





        database=FirebaseDatabase.getInstance();
        userQrcodelistRef =database.getReference().child("DosmEvent").child("Users").child(currentuser.getUid()).child("qrcodesScanned");
        userDataRef =database.getReference().child("DosmEvent").child("Users").child(currentuser.getUid());
        userDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("name").getValue(String.class);
                username.setText("Username: "+dataSnapshot.child("name").getValue(String.class));
                totalPoints.setText("Total points: "+String.valueOf(dataSnapshot.child("totalpoints").getValue(Long.class)));
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color = generator.getColor(name);
                drawable = TextDrawable.builder()
                        .beginConfig()
                        .width(100)
                        .height(100)
                        .endConfig()
                        .buildRect(Character.toString(name.charAt(0)).toUpperCase(), color);
                profilePic.setImageDrawable(drawable);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        userQrcodelistRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                qrcodeList.clear();
                for( DataSnapshot child : dataSnapshot.getChildren())
                {
                    String qrcode =child.child("qrcode").getValue(String.class);
                    Long points =child.child("points").getValue(Long.class);
                    String descrip =child.child("descrip").getValue(String.class);
                    qrcodeList.add(qrcodeList.size(),new ProfileModal(qrcode,descrip,points,qrcodeList.size()+1));

                }
                ProfileFragmentAdapter updatedAdapter =new ProfileFragmentAdapter(qrcodeList);
                recyclerView.setAdapter(updatedAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}