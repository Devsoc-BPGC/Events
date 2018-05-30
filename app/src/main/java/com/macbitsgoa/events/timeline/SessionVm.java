package com.macbitsgoa.events.timeline;

import androidx.lifecycle.ViewModel;

/**
 * @author Rushikesh Jogdand.
 */
public class SessionVm extends ViewModel {
    public Session getSession() {
        final Session session = new Session();
        session.duration = "50";
        session.eventName = "Robot Wars";
        session.startTime = "01,06,2018,10,10";
        session.session = "Eliminations";
        return session;
    }
}
