package com.macbitsgoa.events.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.macbitsgoa.events.BuildConfig;
import com.macbitsgoa.events.R;

/**
 * Main Activity of the App.
 *
 * @author Rushikesh Jogdand [rushikeshjogdand1@gmail.com]
 */
@SuppressLint("GoogleAppIndexingApiWarning")
public class HomeActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView dummyTv = findViewById(R.id.tv_dummy);

        // example use of build config variable
        if (BuildConfig.eateries) {
            dummyTv.setText(R.string.test_string_eateries);
        }

    }
}
