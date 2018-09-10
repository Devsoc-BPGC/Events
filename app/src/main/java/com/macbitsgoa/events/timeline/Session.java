package com.macbitsgoa.events.timeline;

import androidx.annotation.NonNull;

/**
 * Virtual table to hold data for sessions.
 * See {@link TimelineDao#getSessionsOnDay(String)} to see how this is
 * constructed.
 *
 * @author Rushikesh Jogdand.
 */
public class Session {
    @NonNull
    public String eventId;

    // name of the session
    public String session;

    public String time;

    public String venue;

    // name of event
    @NonNull
    public String name;

    public String description;

    public String eventType;

    public String imageUrl;

    public String ruleBookUrl;
}
