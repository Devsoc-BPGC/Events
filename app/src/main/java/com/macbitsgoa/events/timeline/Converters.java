package com.macbitsgoa.events.timeline;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

/**
 * Converter to add more data types to room.
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
        //CHECKSTYLE.OFF: ArrayTypeStyle
        final String parseRes[] = csString.split("[ ,]");
        //CHECKSTYLE.ON: ArrayTypeStyle
        return Arrays.asList(parseRes);
    }

    /**
     * Combines list of tags to comma separated string.
     * @param tags list of strings.
     * @return cs string.
     */
    @TypeConverter
    public static String combineTags(@NonNull final Iterable<String> tags) {
        final StringBuilder combination = new StringBuilder(0);
        for (final String tag : tags) {
            combination.append(",").append(tag);
        }
        return combination.toString();
    }
}
