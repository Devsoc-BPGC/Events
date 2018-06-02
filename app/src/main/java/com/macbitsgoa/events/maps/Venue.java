package com.macbitsgoa.events.maps;

class Venue {

    private final String latitude;
    private final String longitude;
    private final String venueName;

    //required for using getValue() of DataSnapshot class



    Venue(final String latitude, final String longitude, final String venueName) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.venueName = venueName;

    }


    public String getVenueName() {
        return venueName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
