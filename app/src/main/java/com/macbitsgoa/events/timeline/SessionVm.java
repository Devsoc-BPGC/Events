package com.macbitsgoa.events.timeline;

import androidx.lifecycle.ViewModel;

/**
 * ViewModel for {@link Session}
 *
 * @author Rushikesh Jogdand.
 */
public class SessionVm extends ViewModel {
    @SuppressWarnings("MethodMayBeStatic")
    public Session getSession() {
        final Session session = new Session();
        session.duration = "50";
        session.eventName = "Robot Wars";
        session.startTime = "01,06,2018,10,10";
        session.session = "Eliminations";
        return session;
    }
}
