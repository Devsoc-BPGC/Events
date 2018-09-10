package com.macbitsgoa.events.timeline;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * The day table. (relation: many - many)
 * @author Rushikesh Jogdand.
 */
@Entity(primaryKeys = {"eventId", "day"},
        foreignKeys = @ForeignKey(entity = Event.class,
                parentColumns = "id",
                childColumns = "eventId"))
public class Day {

    @NonNull
    public String eventId; // not in firebase, should be set manually

    @NonNull
    public String day; // key of the day in firebase ex "0", "1", ...

    public String session; // name of session

    public String time; // start time

    public String venue;
}
