package com.macbitsgoa.events.timeline;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Model and table for an event.
 *
 * @author Rushikesh Jogdand.
 */
@Entity
public class Event {
    public String name;

    @PrimaryKey
    @NonNull
    public String id;

    public String description;

    public String eventType;

    public String imageUrl;

    public String ruleBookUrl;

    @Ignore
    public String eventId; // only used in firebase

    /**
     * To be read from table {@link Category}.
     */
    @Ignore
    public List<String> categories;
}
