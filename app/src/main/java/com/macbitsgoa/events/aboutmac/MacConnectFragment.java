package com.macbitsgoa.events.aboutmac;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.R;

import androidx.fragment.app.Fragment;

public class MacConnectFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mac_connect, container, false);

        linkIcons();
        return view;
    }

    /**
     * Creates the MacConnect fragment in AboutMacActivity.
     */
    public static MacConnectFragment newInstance() {
        MacConnectFragment macConnectFragment = new MacConnectFragment();
        Bundle bundle = new Bundle();
        macConnectFragment.setArguments(bundle);
        return macConnectFragment;
    }

    private void linkIcons() {
        SimpleDraweeView facebookConnect =
                (SimpleDraweeView)view.findViewById(R.id.facebook_connect);
        facebookConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(getResources().getString(R.string.mac_facebook_url));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        SimpleDraweeView playStoreConnect =
                (SimpleDraweeView)view.findViewById(R.id.playstore_connect);
        playStoreConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(getResources().getString(R.string.mac_playstore_url));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        SimpleDraweeView linkedinConnect =
                (SimpleDraweeView)view.findViewById(R.id.linkedin_connect);
        linkedinConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(getResources().getString(R.string.mac_linkedin_url));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}