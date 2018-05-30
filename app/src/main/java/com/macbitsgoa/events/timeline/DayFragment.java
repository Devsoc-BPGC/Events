package com.macbitsgoa.events.timeline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macbitsgoa.events.R;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.macbitsgoa.events.Utilities.TAG_PREFIX;

/**
 * @author Rushikesh Jogdand.
 */
public class DayFragment extends Fragment {

    @SuppressWarnings("WeakerAccess")
    static final String TAG = TAG_PREFIX + DayFragment.class.getSimpleName();
    private static final String ARG_DATE = "date";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://us-central1-events-f6847.cloudfunctions.net")
            .build();
    private final WebService service =
            retrofit.create(WebService.class);
    @SuppressWarnings("WeakerAccess")
    TextView placeholderTv;
    private Call<ResponseBody> testCall = service.testApi();

    static DayFragment getInstance(@NonNull final String date) {
        final DayFragment fragment = new DayFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_day, container,
                false);
        placeholderTv = rootView.findViewById(R.id.tv_fragment_day_placeholder);
        if (getArguments() != null) {
            final String date = getArguments().getString(ARG_DATE);
            placeholderTv.setText(date);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        testCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(final Call<ResponseBody> call,
                                   final Response<ResponseBody> response) {
                final ResponseBody body = response.body();
                if (body == null) {
                    Log.e(TAG, "testCall response is null : ");
                    return;
                }
                try {
                    placeholderTv.setText(body.string());
                } catch (final IOException e) {
                    Log.e(TAG, e.getMessage(), e.fillInStackTrace());
                }
            }

            @Override
            public void onFailure(final Call<ResponseBody> call,
                                  final Throwable t) {
                Log.e(TAG, t.getMessage(), t.fillInStackTrace());
            }
        });
        testCall = testCall.clone();
    }
}
