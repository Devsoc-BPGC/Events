package com.macbitsgoa.events.aboutmac;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.SocialActivity;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import static com.macbitsgoa.events.Utilities.FB_Q;
import static com.macbitsgoa.events.Utilities.FB_URL;
import static com.macbitsgoa.events.Utilities.LINKEDIN_URL;
import static com.macbitsgoa.events.Utilities.PLAY_STORE_DEV_URL;

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
                view.findViewById(R.id.facebook_connect);
        facebookConnect.setOnClickListener(view -> {
            final CustomTabsIntent intent=new CustomTabsIntent.Builder().build();
            intent.launchUrl(getContext(), Uri.parse(FB_URL));
        });

        SimpleDraweeView playStoreConnect =
                view.findViewById(R.id.playstore_connect);
        playStoreConnect.setOnClickListener(view -> {
            final CustomTabsIntent intent=new CustomTabsIntent.Builder().build();
            intent.launchUrl(getContext(), Uri.parse(PLAY_STORE_DEV_URL));
        });

        SimpleDraweeView linkedinConnect =
                view.findViewById(R.id.linkedin_connect);
        linkedinConnect.setOnClickListener(view -> {
            final CustomTabsIntent intent=new CustomTabsIntent.Builder().build();
            intent.launchUrl(getContext(), Uri.parse(LINKEDIN_URL));
        });
    }
}