package com.macbitsgoa.events;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Class to store all Firebase Keys.
 *
 * @author Rajath Reghunath.
 */

public final class FirebaseKeys {

    public static final String MAP_CENTRAL_LOC = "/locations/central";
    public static final String MAP_ZOOM_SETTINGS = "/locations/zoom";
    public static final String MAP_MARKERS_LOC = "/locations/locations";

    public static final String ABOUT_FEST = "aboutfest";

    public static final String TOPIC_ABOUT_APP = "aboutAppUpdate";

    public static final String FCM_KEY_TYPE = "type";

    public static final DatabaseReference DB_REF = FirebaseDatabase.getInstance().getReference();

    public static final DatabaseReference FB_TIMELINE_REF = DB_REF.child("timeline");

    public static final String DB_KEY_CAT = "category";

    public static final String DB_KEY_DAY = "days";
}
