package com.macbitsgoa.events.timeline;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

/**
 * @author Rushikesh Jogdand.
 */
@SuppressWarnings({"DuplicateStringLiteralInspection", "unused"})
@Entity(primaryKeys = {"eventName", "session"},
        foreignKeys = {
                @ForeignKey(entity = Venue.class,
                        parentColumns = "id",
                        childColumns = "venue",
                        onUpdate = CASCADE,
                        onDelete = CASCADE),
                @ForeignKey(entity = Event.class,
                        parentColumns = "name",
                        childColumns = "eventName",
                        onDelete = CASCADE,
                        onUpdate = CASCADE)
        },
        indices = {
                @Index("venue")
        }
)
public class Session {
    @NonNull
    public String eventName;
    @NonNull
    public String session;

    public String venue;
    public String tags; // comma `,` separated strings
    public String startTime; // "dd,mm,yyyy,hh,mm"
    public String duration;
}
