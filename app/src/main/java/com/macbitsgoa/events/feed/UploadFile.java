package com.macbitsgoa.events.feed;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static android.webkit.MimeTypeMap.getFileExtensionFromUrl;
import static com.macbitsgoa.events.Utilities.TAG_PREFIX;
import static com.macbitsgoa.events.feed.MetaDataAndPermissions.AUTHORIZATION_FIELD_KEY;
import static com.macbitsgoa.events.feed.MetaDataAndPermissions.AUTHORIZATION_FIELD_VALUE_PREFIX;

/**
 * Code to upload file to google drive.
 *
 * @author aayushSingla
 */
public class UploadFile extends AsyncTask<Void, Void, Void> {
    private static final String TAG = TAG_PREFIX + UploadFile.class.getSimpleName();
    private final String path;
    private final String accessToken;
    private String fileId;
    private final String imageDesc;

    /**
     * Constructor to create instance of this call.
     *
     * @param path        the path of file to be uploaded
     * @param accessToken the access token retrieved from google sign in
     * @param imageDesc   description of image to be uploaded
     */
    UploadFile(final String path, final String accessToken, final String imageDesc) {
        this.path = path;
        this.accessToken = accessToken;
        this.imageDesc = imageDesc;
    }

    @Override
    protected Void doInBackground(final Void... voids) {
        final String response = uploadFile();

        try {
            final JSONObject jsonObject = new JSONObject(response);
            fileId = (String) jsonObject.get("id");
        } catch (final JSONException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
        }

        return null;
    }

    private String uploadFile() {
        try {
            final File file = new File(path);
            final OkHttpClient okHttpClient = new OkHttpClient();
            final long fileSize = file.length();
            final RequestBody requestBody = RequestBody.create(MediaType.parse(getMimeType(path)),
                    fileToBytes(file));

            final String driveUploadUrl = "https://www.googleapis.com/upload/drive/v3/files?uploadType=media";
            final Request request = new Request.Builder()
                    .url(driveUploadUrl)
                    .addHeader("Content-Type", getMimeType(path))
                    .addHeader("Content-Length", String.valueOf(fileSize))
                    .addHeader(AUTHORIZATION_FIELD_KEY,
                            AUTHORIZATION_FIELD_VALUE_PREFIX + accessToken)
                    .post(requestBody)
                    .build();
            final Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (final Exception e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
        }
        return null;
    }

    private static byte[] fileToBytes(final File file) {
        byte[] bytes = new byte[0];
        try (final FileInputStream inputStream = new FileInputStream(file)) {
            bytes = new byte[inputStream.available()];
            //noinspection ResultOfMethodCallIgnored
            inputStream.read(bytes);
        } catch (final IOException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
        }
        return bytes;
    }

    private static String getMimeType(final String filePath) {
        String type = null;
        final String extension = getFileExtensionFromUrl(filePath);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


    @Override
    protected void onPreExecute() {
        Log.i(TAG, "Uploading file");
    }

    @Override
    protected void onPostExecute(final Void param) {
        final MetaDataAndPermissions mdp;
        mdp = new MetaDataAndPermissions(fileId, accessToken, imageDesc);
        mdp.execute();
    }


}