package com.macbitsgoa.events;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class Events extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
