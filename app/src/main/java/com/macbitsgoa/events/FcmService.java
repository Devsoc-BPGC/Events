package com.macbitsgoa.events;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.macbitsgoa.events.FirebaseKeys.FCM_KEY_TYPE;
import static com.macbitsgoa.events.FirebaseKeys.TOPIC_ABOUT_APP;
import static com.macbitsgoa.events.PrefHelper.KEY_ABOUT_FEST;
import static com.macbitsgoa.events.PrefHelper.getPref;

public class FcmService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        if (TOPIC_ABOUT_APP.equals(remoteMessage.getData().get(FCM_KEY_TYPE))) {
            // Please note: We're putting about fest data in shared pref because it is very
            // small in quantity. DO NOT take this as a pattern to be followed.
            getPref(getApplicationContext())
                    .edit()
                    .putString(KEY_ABOUT_FEST, remoteMessage.getData().get("value"))
                    .apply();
        }
    }
}
