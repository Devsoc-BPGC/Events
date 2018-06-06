package com.macbitsgoa.events.timeline;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.Response;

import static com.macbitsgoa.events.Utilities.TAG_PREFIX;

/**
 * Single source of data for {@link Session}.
 *
 * @author Rushikesh Jogdand.
 */
@Singleton
public class SessionsRepository {
    @SuppressWarnings("StringConcatenationMissingWhitespace")
    private static final String TAG
            = TAG_PREFIX + SessionsRepository.class.getSimpleName();
    private final SessionDao sessionDao;
    private final Executor executor;
    private final WebService webService;

    /**
     * Default constructor.
     *
     * @param sessionDao Data Access Object.
     * @param executor   Executor.
     * @param webService {@link WebService} instance.
     */
    @Inject
    public SessionsRepository(final SessionDao sessionDao,
                              final Executor executor,
                              final WebService webService) {
        this.sessionDao = sessionDao;
        this.executor = executor;
        this.webService = webService;
    }

    public LiveData<List<Session>> getSessionsStarting(@NonNull final String startTime) {
        //TODO
        refreshSessions();
        return sessionDao.getSessionsStartingAt(startTime);
    }

    private void refreshSessions() {
        executor.execute(() -> {
            try {
                final Response<List<Session>> response = webService.getAllSessions().execute();
                sessionDao.insertSessionList(response.body());
            } catch (final IOException e) {
                Log.e(TAG, e.getMessage(), e.fillInStackTrace());
            }
        });
    }
}
