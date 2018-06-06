package com.macbitsgoa.events.timeline;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data Access Object for {@link Session}
 *
 * @author Rushikesh Jogdand.
 */
@Dao
public interface SessionDao {

    @Insert
    void insertSessions(Session... sessions);

    @Insert
    void insertSessionList(List<Session> sessions);

    @Update
    void updateSessions(Session... sessions);

    @Delete
    void deleteSessions(Session... sessions);

    @Query("SELECT * FROM Session WHERE startTime = :startTime")
    LiveData<List<Session>> getSessionsStartingAt(String startTime);

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Query("SELECT DISTINCT startTime FROM Session WHERE startTime LIKE :date")
    List<String> getStartTimesForDate(String date);

}
