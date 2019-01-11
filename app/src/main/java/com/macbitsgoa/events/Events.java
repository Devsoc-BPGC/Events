package com.macbitsgoa.events;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import androidx.annotation.NonNull;

import static com.macbitsgoa.events.FirebaseKeys.ABOUT_FEST;
import static com.macbitsgoa.events.FirebaseKeys.TOPIC_ABOUT_APP;
import static com.macbitsgoa.events.PrefHelper.HAS_ABOUT_FEST_DATA;
import static com.macbitsgoa.events.PrefHelper.KEY_ABOUT_FEST;
import static com.macbitsgoa.events.PrefHelper.getPref;
import static com.macbitsgoa.events.Utilities.TAG_PREFIX;

public class Events extends Application {

    private static final String TAG = TAG_PREFIX + Events.class.getSimpleName();

    public static String playStoreLink;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        playStoreLink = "http://play.google.com/store/apps/details?id=" + getPackageName();
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_ABOUT_APP)
                .addOnCompleteListener(task -> {
                    if (BuildConfig.DEBUG) {
                        Log.i(TAG, "subscription to topics" + TOPIC_ABOUT_APP
                                + " isSuccessful : " + task.isSuccessful());
                    }
                });
        if (!getPref(this).getBoolean(HAS_ABOUT_FEST_DATA, false)) {
            getAboutFestData();
        }
    }

    private void getAboutFestData() {
        FirebaseDatabase.getInstance().getReference(ABOUT_FEST)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        // Please note: We're putting about fest data in shared pref because it
                        // is very small in quantity. DO NOT take this as a pattern to be followed.
                        getPref(Events.this)
                                .edit()
                                .putString(KEY_ABOUT_FEST, new Gson()
                                        .toJson(dataSnapshot.getValue()))
                                .putBoolean(HAS_ABOUT_FEST_DATA, true)
                                .apply();
                    }

                    @Override
                    public void onCancelled(@NonNull final DatabaseError databaseError) {
                        Log.e(TAG, databaseError.getMessage(), databaseError.toException());
                    }
                });
    }
}
