package com.macbitsgoa.events.timeline;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Model for venues.
 * @author Rushikesh Jogdand.
 */
@SuppressWarnings("unused")
@Entity
public class Venue {
    @SuppressWarnings("NullableProblems")
    @NonNull
    @PrimaryKey
    public String id;
    public String name;
    public float latitude;
    public float longitude;
}
