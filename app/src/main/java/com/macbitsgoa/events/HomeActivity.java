package com.macbitsgoa.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Main Activity of the App.
 *
 * @author Rushikesh Jogdand [rushikeshjogdand1@gmail.com]
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView dummyTv = findViewById(R.id.tv_dummy);

        // example use of build config variable
        if (BuildConfig.eateries) {
            dummyTv.setText(R.string.test_string_eateries);
        }
    }
}
