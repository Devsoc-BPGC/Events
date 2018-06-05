package com.macbitsgoa.events.timeline;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

/**
 * @author Rushikesh Jogdand.
 */
public class Converters {
    /**
     * Parses tag list.
     *
     * @param csString comma `,` separated tags
     * @return parsed list of tags
     */
    @TypeConverter
    public static List<String> parseTags(@NonNull final String csString) {
        final String parseRes[] = csString.split("[ ,]");
        return Arrays.asList(parseRes);
    }

    @TypeConverter
    public static String combineTags(@NonNull final Iterable<String> tags) {
        final StringBuilder combination = new StringBuilder(0);
        for (final String tag : tags) {
            combination.append(",").append(tag);
        }
        return combination.toString();
    }
}
