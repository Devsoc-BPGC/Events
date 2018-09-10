package com.macbitsgoa.events.timeline;

import android.content.Context;

import com.macbitsgoa.events.BuildConfig;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Database currently used only in timeline.
 * You should not need to use this directly. Instead use {@link TimelineRepo},
 * and add methods to the repo if existing are not sufficient.
 *
 * @author Rushikesh Jogdand.
 */
@Database(entities = {Event.class, Category.class, Day.class}, version = 2)
public abstract class SqlDatabase extends RoomDatabase {
    public static final String DB_NAME = String.format("%s.db", BuildConfig.APPLICATION_ID);

    private static SqlDatabase instance = null;

    public static SqlDatabase getInstance(@NonNull Context context) {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, SqlDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract TimelineDao getTimelineDao();
}
