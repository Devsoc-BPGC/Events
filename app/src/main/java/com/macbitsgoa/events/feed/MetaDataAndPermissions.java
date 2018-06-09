package com.macbitsgoa.events.feed;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import androidx.annotation.Nullable;

import static com.macbitsgoa.events.Utilities.TAG_PREFIX;


/**
 * Request google drive permissions.
 *
 * @author aayush singla
 */

public class MetaDataAndPermissions extends AsyncTask<Void, Void, Void> {
    private static final String FILE_ID_KEY = "fileId";
    private static final String DATE_TIME_KEY = "dateTime";
    private static final String WEB_CONTENT_LINK = "webContentLink";
    @SuppressWarnings("WeakerAccess")
    public static final String AUTHORIZATION_FIELD_KEY = "Authorization";
    @SuppressWarnings("WeakerAccess")
    public static final String AUTHORIZATION_FIELD_VALUE_PREFIX = "Bearer ";
    private static final String driveApiBaseUrl = "https://www.googleapis.com/drive/v3/files/";
    private static final String defaultProfileUrl = "https://nush.sg/img/default-profile.png";
    private static final String TAG = TAG_PREFIX + MetaDataAndPermissions.class.getSimpleName();
    private static final String KEY_OWNER_IMAGE = "ownerImage";
    private static final String KEY_JSON_PHOTO_LINK = "photoLink";
    private final String fileId;
    private final String accessToken;
    private final String imageDesc;

    /**
     * Default constructor.
     *
     * @param fileId      id of the file.
     * @param accessToken obtained from sign in.
     * @param imageDesc   description of image to be uploaded
     */
    @SuppressWarnings("WeakerAccess")
    public MetaDataAndPermissions(final String fileId,
                                  final String accessToken, final String imageDesc) {
        this.fileId = fileId;
        this.accessToken = accessToken;
        this.imageDesc = imageDesc;
    }


    @Override
    protected Void doInBackground(final Void... voids) {
        try {
            getPermissions();
            final JSONObject metaData = getMetadata();
            if (metaData == null) {
                Log.e(TAG, "Received null metadata, returning");
                return null;
            }
            Log.e(TAG, metaData.toString());
            pushToFirebase(getMetadata());
        } catch (final JSONException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "Uploading file and granting permissions");
    }

    @Override
    protected void onPostExecute(final Void result) {
        Log.i(TAG, "Permissions granted and pushed to firebase");

    }

    private void getPermissions() throws JSONException {

        final JSONObject jsonPermission = new JSONObject()
                .put("role", "reader")
                .put("type", "anyone")
                .put("allowFileDiscovery", "true");

        final OkHttpClient client = new OkHttpClient();
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                jsonPermission.toString());

        final Request permission = new Request.Builder()
                .addHeader(AUTHORIZATION_FIELD_KEY, AUTHORIZATION_FIELD_VALUE_PREFIX + accessToken)
                .url(driveApiBaseUrl + fileId + "/permissions")
                .post(requestBody)
                .build();

        try {
            client.newCall(permission).execute();
        } catch (final IOException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
        }

    }

    @Nullable
    private JSONObject getMetadata() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(driveApiBaseUrl + fileId + "?access_token=" + accessToken + "&fields=*")
                .get()
                .build();
        try {
            final Response response = okHttpClient.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (final IOException | JSONException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
        }

        return null;
    }


    private void pushToFirebase(final JSONObject jsonObject) throws JSONException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        final String current = sdf.format(new Date());
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference()
                .child("feed")
                .child(current);

        final String webLink = (String) jsonObject.get(WEB_CONTENT_LINK);
        final JSONObject ownerObject = (JSONObject) jsonObject.getJSONArray("owners").get(0);
        final String owner = (String) ownerObject.get("displayName");
        final HashMap<String, String> hashMap = jsonToMap(jsonObject.toString());
        if (ownerObject.has(KEY_JSON_PHOTO_LINK)) {
            final String ownerImage = (String) ownerObject.get(KEY_JSON_PHOTO_LINK);
            dbRef.child(KEY_OWNER_IMAGE).setValue(ownerImage);
        } else {
            dbRef.child(KEY_OWNER_IMAGE).setValue(defaultProfileUrl);
        }


        dbRef.child(DATE_TIME_KEY).setValue(current);
        dbRef.child(FILE_ID_KEY).setValue(fileId);
        dbRef.child("imageUrl").setValue(webLink);
        dbRef.child("numberLikes").setValue(0);
        dbRef.child("owner").setValue(owner);
        dbRef.child("meta-data").setValue(hashMap);
        dbRef.child("desc").setValue(imageDesc);

    }

    private static HashMap<String, String> jsonToMap(final String t) throws JSONException {
        final HashMap<String, String> map = new HashMap<>(0);
        final JSONObject jObject = new JSONObject(t);
        final Iterator<?> keys = jObject.keys();

        while (keys.hasNext()) {
            final String key = (String) keys.next();
            final String value = jObject.getString(key);
            map.put(key, value);
        }
        return map;
    }


}