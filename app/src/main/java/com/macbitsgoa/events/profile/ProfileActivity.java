package com.macbitsgoa.events.profile;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Fragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.macbitsgoa.events.Browser;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeActivity;


import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);









                 loadFragment(new ProfileFragment());

    }
    private void loadFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace( R.id.fragment_container,fragment);
        ft.commit();

    }}