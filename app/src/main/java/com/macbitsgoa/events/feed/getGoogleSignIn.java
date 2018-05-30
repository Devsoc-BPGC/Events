package com.macbitsgoa.events.feed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.api.services.drive.DriveScopes;
import com.macbitsgoa.events.R;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Aayush Singla
 *         This Activity can be used to make signIn available at specific places in app.
 *         use onActivityResult() in the calling method to get the result.
 *         use Intent.getExtra() using "account" to get the user account
 */

public class getGoogleSignIn extends Activity {
    private static final String TAG = "TAG";
    private static final int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(DriveScopes.DRIVE))
                .requestIdToken(getString(R.string.server_client_id))
                .requestServerAuthCode(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignI\nClient with the options specified by gso.
        mGoogleSignInClient = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this, gso);

        final Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            final Task<GoogleSignInAccount> task = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(final Task<GoogleSignInAccount> completedTask) {
        try {
            final GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            returnResult(account);
        } catch (final ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            returnResult(null);
        }
    }

    /**
     * @param account this method gets signedIn account and returns it to calling activity
     */
    void returnResult(final GoogleSignInAccount account) {
        if (account != null) {
            final String accessToken = firebaseAuthWithGoogle(account);
            final Intent intent = new Intent();
            intent.putExtra("account", account);
            intent.putExtra("token", accessToken);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     * This method checks onStart of the activity if the user has already signed in for
     * any other feature in the app.
     */
    @Override
    protected void onStart() {
        super.onStart();
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        returnResult(account);
    }

    private String firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final String authCode = account.getServerAuthCode();

        final OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = null;

        if (authCode != null) {
            requestBody = new FormEncodingBuilder()
                    .add("grant_type", "authorization_code")
                    .add("client_id", getString(R.string.server_client_id))
                    .add("client_secret", getString(R.string.server_client_secret))
                    .add("redirect_uri", getString(R.string.redirect_url))
                    .add("code", authCode)
                    .build();
        }

        final Request request = new Request.Builder()
                .url("https://www.googleapis.com/oauth2/v4/token")
                .post(requestBody)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject jsonObject = new JSONObject(response.body().string());
            return (String) jsonObject.get("access_token");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
