package com.macbitsgoa.events.timeline;

import android.app.Application;
import android.os.AsyncTask;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

import static com.macbitsgoa.events.FirebaseKeys.DB_KEY_CAT;
import static com.macbitsgoa.events.FirebaseKeys.DB_KEY_DAY;
import static com.macbitsgoa.events.FirebaseKeys.FB_TIMELINE_REF;

/**
 * "Single source of data" for timeline section. Execute all CRUD operations here.
 *
 * @author Rushikesh Jogdand.
 */
public class TimelineRepo {
    private TimelineDao dao;

    public TimelineRepo(final Application app) {
        dao = SqlDatabase.getInstance(app).getTimelineDao();
        fetchData();
    }

    public void fetchData() {
        FB_TIMELINE_REF.addValueEventListener(new FirebaseListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                List<Category> categories = new ArrayList<>();
                List<Event> events = new ArrayList<>();
                List<Day> days = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Event event = child.getValue(Event.class);
                    event.id = event.eventId;
                    events.add(event);
                    for (DataSnapshot cat : child.child(DB_KEY_CAT).getChildren()) {
                        String name = cat.getValue(String.class);
                        categories.add(new Category(name, event.id));
                    }
                    for (DataSnapshot day : child.child(DB_KEY_DAY).getChildren()) {
                        Day d = day.getValue(Day.class);
                        d.day = day.getKey();
                        d.eventId = event.id;
                        days.add(d);
                    }
                }
                new EventInsertion(dao).execute(new InsertionData(events, categories, days));
            }
        });
    }

    public LiveData<List<Session>> getSessionsOfDay(int day) {
        return dao.getSessionsOnDay(Integer.toString(day));
    }

    private static class EventInsertion extends AsyncTask<InsertionData, Void, Void> {

        private TimelineDao dao;

        private EventInsertion(final TimelineDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final InsertionData... todo) {
            for (InsertionData data : todo) {
                for (Event e : data.events) {
                    dao.addEvent(e);
                }
                for (Category c : data.categories) {
                    dao.addCategory(c);
                }
                for (Day d : data.days) {
                    dao.addDay(d);
                }
            }
            return null;
        }
    }

    private static final class InsertionData {
        public List<Event> events;
        public List<Category> categories;
        public List<Day> days;

        public InsertionData(final List<Event> events, final List<Category> categories, final List<Day> days) {
            this.events = events;
            this.categories = categories;
            this.days = days;
        }
    }
}
