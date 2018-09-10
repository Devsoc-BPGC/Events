package com.macbitsgoa.events.timeline;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.macbitsgoa.events.Utilities.TAG_PREFIX;

/**
 * Use this instead of {@link ValueEventListener} so that you won't have to catch
 * and log errors.
 *
 * @author Rushikesh Jogdand.
 */
public abstract class FirebaseListener implements ValueEventListener {
    private static final String TAG = TAG_PREFIX + FirebaseListener.class.getSimpleName();

    @Override
    public void onCancelled(final DatabaseError databaseError) {
        Log.e(TAG, databaseError.getMessage(), databaseError.toException());
    }
}
