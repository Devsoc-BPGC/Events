package com.macbitsgoa.events.timeline;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data Access Object for {@link Event}.
 *
 * @author Rushikesh Jogdand.
 */
@Dao
public interface EventDao {
    @Insert
    void insertEvents(Event... events);

    @Update
    void updateEvents(Event... events);

    @Query("SELECT * FROM Event")
    LiveData<List<Event>> getAllEvents();

}
