package com.macbitsgoa.events.timeline;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * @author Rushikesh Jogdand.
 */
@Database(entities = {Venue.class, Event.class, Session.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SqlDatabase extends RoomDatabase {
    public abstract VenueDao venueDao();

    public abstract EventDao eventDao();

    public abstract SessionDao sessionDao();
}
