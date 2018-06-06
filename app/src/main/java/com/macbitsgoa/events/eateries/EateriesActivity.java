package com.macbitsgoa.events.eateries;

import android.app.Activity;
import android.os.Bundle;

import com.macbitsgoa.events.R;

public class EateriesActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eateries);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
