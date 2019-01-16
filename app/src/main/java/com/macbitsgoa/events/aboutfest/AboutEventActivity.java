package com.macbitsgoa.events.aboutfest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.eateries.EateriesActivity;
import com.macbitsgoa.events.home.HomeActivity;

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
        toolbar.setTitle(getString(R.string.about_fest));
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(AboutEventActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
