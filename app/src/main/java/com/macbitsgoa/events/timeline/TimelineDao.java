package com.macbitsgoa.events.timeline;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Data Access Object for timeline feature. Define CRUD db queries here and
 * execute those queries from {@link TimelineRepo}.
 *
 * @author Rushikesh Jogdand.
 */
@Dao
public abstract class TimelineDao {
    @Insert(onConflict = REPLACE)
    public abstract long addEvent(Event event);

    @Insert(onConflict = REPLACE)
    public abstract long addDay(Day day);

    @Insert(onConflict = REPLACE)
    public abstract long addCategory(Category category);

    @Query("SELECT eventId, name, description, eventType, " +
            "imageUrl, ruleBookUrl, session, time, venue " +
            "FROM Event INNER JOIN Day " +
            "ON Day.eventId = Event.id " +
            "WHERE Day.day = :day " +
            "ORDER BY time")
    public abstract LiveData<List<Session>> getSessionsOnDay(String day);

}
