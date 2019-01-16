package com.macbitsgoa.events;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.eateries.EateriesActivity;
import com.macbitsgoa.events.home.HomeActivity;

import static com.macbitsgoa.events.Utilities.FB_Q;
import static com.macbitsgoa.events.Utilities.INS_Q;
import static com.macbitsgoa.events.Utilities.LI_Q;
import static com.macbitsgoa.events.Utilities.TW_Q;

public class SocialActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        final Toolbar toolbar = findViewById(R.id.social_toolbar);
        toolbar.setTitle(getString(R.string.social));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(SocialActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Fresco.initialize(this);
        SimpleDraweeView facebookConnect = findViewById(R.id.fb_q);
        facebookConnect.setOnClickListener(view -> {
            Uri uri = Uri.parse(FB_Q);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        SimpleDraweeView instagramConnect = findViewById(R.id.ins_q);
        instagramConnect.setOnClickListener(view -> {
            Uri uri = Uri.parse(INS_Q);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        SimpleDraweeView twitterConnect = findViewById(R.id.tw_q);
        twitterConnect.setOnClickListener(view -> {
            Uri uri = Uri.parse(TW_Q);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        SimpleDraweeView linkedInConnect = findViewById(R.id.li_q);
        linkedInConnect.setOnClickListener(view -> {
            Uri uri = Uri.parse(LI_Q);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }
}
