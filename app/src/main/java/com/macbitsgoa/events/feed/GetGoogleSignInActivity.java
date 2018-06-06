package com.macbitsgoa.events.feed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

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

import static com.macbitsgoa.events.Utilities.TAG_PREFIX;

/**
 * This Activity can be used to make signIn available at specific places in app.
 * use onActivityResult() in the calling activity to get the result.
 * use Intent.getExtra() using key as KEY_ACCOUNT to get the user account
 * and KEY_TOKEN to get access token
 *
 * @author Aayush Singla
 */

public class GetGoogleSignInActivity extends Activity {
    private static final String TAG = TAG_PREFIX + GetGoogleSignInActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 0;
    public static final String KEY_TOKEN = "token";
    public static final String KEY_ACCOUNT = "account";
    private static final int ERROR_CODE_PERMISSION_DENIED = 12501;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(DriveScopes.DRIVE))
                .requestIdToken(getString(R.string.server_client_id))
                .requestServerAuthCode(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignI\nClient with the options specified by gso.
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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
            final Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
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
            if (e.getStatusCode() == ERROR_CODE_PERMISSION_DENIED) {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Your request can't be processed.Please try again later.",
                        Toast.LENGTH_LONG).show();
            }
            returnResult(null);
        }
    }

    private void returnResult(final GoogleSignInAccount account) {
        if (account != null) {
            final String accessToken = firebaseAuthWithGoogle(account);
            final Intent intent = new Intent();
            intent.putExtra(KEY_ACCOUNT, account);
            intent.putExtra(KEY_TOKEN, accessToken);
            setResult(RESULT_OK, intent);
            finish();
        } else {
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
        if (account != null) {
            returnResult(account);
        }
    }

    private String firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        final StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder()
                .permitAll()
                .build();
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
        } else {
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }

        final Request request = new Request.Builder()
                .url("https://www.googleapis.com/oauth2/v4/token")
                .post(requestBody)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject jsonObject = new JSONObject(response.body().string());
            return (String) jsonObject.get("access_token");

        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
        }
        return null;
    }
}
