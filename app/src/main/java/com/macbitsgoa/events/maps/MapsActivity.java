package com.macbitsgoa.events.maps;


import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

/**
 * Google Maps Activity showing Event Markers.
 *
 * @author Rajath Reghunath [rrgodhorus@gmail.com]
 */

@SuppressWarnings("DuplicateStringLiteralInspection")
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int MAP_INIT_CAP = 15;  //initial capacity for number of venues
    private static final String MAP_CENTRAL_LOC = "/locations/central";
    private static final String MAP_ZOOM_SETTINGS = "/locations/zoom";
    private static final String MAP_MARKERS_LOC = "/locations/locations";
    private static final float MAP_DEFAULT_ZOOM = 16.0f;
    private static final String FIREBASE_ERROR_TAG = "MapsActivity";
    private static final String FIREBASE_ERROR_STRING = "Firebase data retrieval failed";
    private static final String FIREBASE_STRING_LATITUDE = "latitude";
    private static final String FIREBASE_STRING_LONGITUDE = "longitude";
    private static final String FIREBASE_STRING_VENUE_NAME = "venueName";
    private Venue centralVenue;
    private float zoomLevel = MAP_DEFAULT_ZOOM;
    @SuppressWarnings("TypeMayBeWeakened")
    private final ArrayList<Venue> venues  = new ArrayList<>(MAP_INIT_CAP);
    private GoogleMap map;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {

        map = googleMap;
        getLocations();
    }

    @SuppressWarnings("FeatureEnvy")
    private void getLocations() {

        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference(MAP_ZOOM_SETTINGS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    zoomLevel = Float.parseFloat(snapshot.getValue(String.class));

                }
            }

            @Override
            public void onCancelled(@NonNull final DatabaseError databaseError) {
                Log.e(FIREBASE_ERROR_TAG,FIREBASE_ERROR_STRING + " in zoom");
            }
        });

        reference = FirebaseDatabase.getInstance().getReference(MAP_CENTRAL_LOC);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    centralVenue = new Venue(
                            snapshot.child(FIREBASE_STRING_LATITUDE).getValue(String.class),
                            snapshot.child(FIREBASE_STRING_LONGITUDE).getValue(String.class),
                            snapshot.child(FIREBASE_STRING_VENUE_NAME).getValue(String.class)
                                        );

                }
                final LatLng central = new LatLng(Double.parseDouble(centralVenue.getLatitude()),
                        Double.parseDouble(centralVenue.getLongitude()));
//                mMap.addMarker(new MarkerOptions().position(central)
//                        .title(centralVenue.getVenueName()));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,zoomLevel));

            }

            @Override
            public void onCancelled(@NonNull final DatabaseError databaseError) {
                Log.e(FIREBASE_ERROR_TAG,FIREBASE_ERROR_STRING + " in central");
            }
        });

        reference = FirebaseDatabase.getInstance().getReference(MAP_MARKERS_LOC);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                venues.clear();
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    venues.add(new Venue(
                            snapshot.child(FIREBASE_STRING_LATITUDE).getValue(String.class),
                            snapshot.child(FIREBASE_STRING_LONGITUDE).getValue(String.class),
                            snapshot.child(FIREBASE_STRING_VENUE_NAME).getValue(String.class)
                                    )
                    );

                }
                for (final Venue v : venues) {
                    final LatLng loc = new LatLng(Double.parseDouble(v.getLatitude()),
                                            Double.parseDouble(v.getLongitude()));
                    map.addMarker(new MarkerOptions().position(loc).title(v.getVenueName()));

                }

            }

            @Override
            public void onCancelled(@NonNull final DatabaseError databaseError) {
                Log.e(FIREBASE_ERROR_TAG,FIREBASE_ERROR_STRING + " in markers");
            }
        });




    }

}
