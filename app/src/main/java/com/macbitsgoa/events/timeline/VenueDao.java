package com.macbitsgoa.events.timeline;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data Access Object for {@link Venue}
 * @author Rushikesh Jogdand.
 */
@Dao
public interface VenueDao {
    @Insert
    void insertVenues(Venue... venues);

    @Update
    void updateVenues(Venue... venues);

    @Query("SELECT * FROM Venue WHERE id = :venueId")
    Venue getVenue(String venueId);

    @Query("SELECT * FROM Venue")
    LiveData<List<Venue>> getAllVenues();
}
