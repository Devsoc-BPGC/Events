package com.macbitsgoa.events.aboutfest;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.macbitsgoa.events.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.events.PrefHelper.KEY_ABOUT_FEST;
import static com.macbitsgoa.events.PrefHelper.getPref;

public class AboutEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        final Toolbar toolbar = findViewById(R.id.about_event_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        final String aboutFestData = getPref(this).getString(KEY_ABOUT_FEST, "");
        if (aboutFestData.isEmpty()) {
            finish();
        }
        final AboutFest data = new Gson().fromJson(aboutFestData, AboutFest.class);
        ((SimpleDraweeView) findViewById(R.id.sdv_event_logo)).setImageURI(data.logoUrl);
        ((TextView) findViewById(R.id.tv_event_desc)).setText(data.description);
        ((RecyclerView) findViewById(R.id.rv_organisers))
                .setAdapter(new OrganisersAdapter(data.organisers));
    }
}
