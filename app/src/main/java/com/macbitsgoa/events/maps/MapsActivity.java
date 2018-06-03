package com.macbitsgoa.events.maps;


import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.macbitsgoa.events.FirebaseKeys;
import com.macbitsgoa.events.R;

import static com.macbitsgoa.events.Utilities.TAG_PREFIX;

/**
 * Google Maps Activity showing Event Markers.
 *
 * @author Rajath Reghunath [rrgodhorus@gmail.com]
 */

@SuppressWarnings("DuplicateStringLiteralInspection")
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int INIT_CAP = 15;  //initial capacity for number of venues
    private static final float DEFAULT_ZOOM = 16.0f;
    private static final Venue DEFAULT_CENTRAL = new Venue("15.392199","73.878609","BITS GOA");
    private static final String FIREBASE_ERROR_TAG = TAG_PREFIX + " "
                                                    + MapsActivity.class.getSimpleName();
    private float zoomLevel = DEFAULT_ZOOM;
    private final Collection<Venue> venues  = new ArrayList<>(INIT_CAP);
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
     * This is where we can add markers or lines, add listeners or move the camera.
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
        reference = FirebaseDatabase.getInstance().getReference(FirebaseKeys.MAP_ZOOM_SETTINGS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    zoomLevel = Float.parseFloat(snapshot.getValue(String.class));

                }
            }

            @Override
            public void onCancelled(@NonNull final DatabaseError databaseError) {
                Log.e(FIREBASE_ERROR_TAG,databaseError.getMessage(),databaseError.toException());
            }
        });

        reference = FirebaseDatabase.getInstance().getReference(FirebaseKeys.MAP_CENTRAL_LOC);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                Venue centralVenue = DEFAULT_CENTRAL;
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    centralVenue = new Venue(
                            snapshot.child(FirebaseKeys.MAP_LATITUDE).getValue(String.class),
                            snapshot.child(FirebaseKeys.MAP_LONGITUDE).getValue(String.class),
                            snapshot.child(FirebaseKeys.MAP_VENUE_NAME).getValue(String.class)
                                        );

                }
                final LatLng central = new LatLng(Double.parseDouble(centralVenue.getLatitude()),
                        Double.parseDouble(centralVenue.getLongitude()));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,zoomLevel));

            }

            @Override
            public void onCancelled(@NonNull final DatabaseError databaseError) {
                Log.e(FIREBASE_ERROR_TAG,databaseError.getMessage(),databaseError.toException());
            }
        });

        reference = FirebaseDatabase.getInstance().getReference(FirebaseKeys.MAP_MARKERS_LOC);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                venues.clear();
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    venues.add(new Venue(
                            snapshot.child(FirebaseKeys.MAP_LATITUDE).getValue(String.class),
                            snapshot.child(FirebaseKeys.MAP_LONGITUDE).getValue(String.class),
                            snapshot.child(FirebaseKeys.MAP_VENUE_NAME).getValue(String.class)
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
                Log.e(FIREBASE_ERROR_TAG,databaseError.getMessage(),databaseError.toException());
            }
        });




    }

}
