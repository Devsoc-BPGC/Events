package com.macbitsgoa.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import static com.macbitsgoa.events.Utilities.FB_Q;
import static com.macbitsgoa.events.Utilities.INS_Q;
import static com.macbitsgoa.events.Utilities.TW_Q;

public class SocialActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
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
    }
}
