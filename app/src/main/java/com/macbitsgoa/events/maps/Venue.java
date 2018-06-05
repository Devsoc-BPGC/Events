package com.macbitsgoa.events.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressWarnings({"WeakerAccess", "unused"})
class Venue {

    public float latitude;
    public float longitude;
    public String venueName;

    //required for using getValue() of DataSnapshot class

    private Venue() {

    }

    Venue(final float latitude, final float longitude, final String venueName) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.venueName = venueName;

    }

    public LatLng getLatLng() {
        return new LatLng(latitude,longitude);
    }

    public MarkerOptions getMarker() {
        return new MarkerOptions().position(getLatLng()).title(venueName);
    }



}
