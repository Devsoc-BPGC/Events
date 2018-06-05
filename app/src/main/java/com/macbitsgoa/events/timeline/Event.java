package com.macbitsgoa.events.timeline;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author Rushikesh Jogdand.
 */
@Entity
public class Event {
    @PrimaryKey
    @NonNull
    public String name;
}
