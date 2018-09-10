package com.macbitsgoa.events.timeline;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * The category table. (Many - many relation)
 * @author Rushikesh Jogdand.
 */
@Entity(primaryKeys = {"name", "eventId"})
@ForeignKey(entity = Event.class,
        parentColumns = "id",
        childColumns = "eventId",
        onDelete = CASCADE)
public class Category {
    @NonNull
    public String name;

    @NonNull
    public String eventId;

    public Category(final String name, final String eventId) {
        this.name = name;
        this.eventId = eventId;
    }
}
