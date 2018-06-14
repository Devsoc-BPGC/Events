package com.macbitsgoa.events;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;


public final class PrefHelper {

    public static final String PREF_NAME = "mac.events" + PrefHelper.class.getPackage().getName();

    public static final String HAS_ABOUT_FEST_DATA = "hasAboutFestData";

    /* Value corresponding to this key is all data about the fest  */
    public static final String KEY_ABOUT_FEST = "aboutFest";

    public static SharedPreferences getPref(@NonNull final Context applicationContext) {
        return applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
}
