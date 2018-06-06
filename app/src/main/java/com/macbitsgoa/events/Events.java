package com.macbitsgoa.events;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class Events extends Application {

    public static String playStoreLink;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        playStoreLink = "http://play.google.com/store/apps/details?id=" + getPackageName();
    }
}
