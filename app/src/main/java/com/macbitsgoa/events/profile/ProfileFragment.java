package com.macbitsgoa.events.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeCardInterface;
import com.macbitsgoa.events.speakers.SpeakersActivity;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    public RecyclerView recyclerView;
    public View v;
    public RecyclerView.Adapter adapter;
    private ArrayList<Profile> qrcodeList = new ArrayList<>(0);


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_profile, container, false);


        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.qrcoderecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        qrcodeList.add(0,new Profile("1234"));
        qrcodeList.add(1,new Profile("3456"));
        qrcodeList.add(2,new Profile("skj"));
        adapter = new ProfileAdapter(qrcodeList); //ChangeEnded
        recyclerView.setAdapter(adapter);

    }


}
