package com.macbitsgoa.events.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.events.BuildConfig;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeCardInterface;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.fragment.app.Fragment;


/**
 * The fragment for feature eateries to be shown on home screen
 * {@link com.macbitsgoa.events.home.HomeActivity}.
 */
public class TimerCardFragment extends Fragment
        implements HomeCardInterface {

    String endTime = "31.10.2019, 17:30:00";
    TextView tv1, tv2;
    SimpleDraweeView q19;
    private static final String TAG = TimerCardFragment.class.getSimpleName();
    CountDownTimer backcounter;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        final MaterialCardView selfCard =
                (MaterialCardView) inflater.inflate(R.layout.fragment_timer_card,
                        container, false);
         tv1= (TextView) selfCard.findViewById(R.id.timer_text_day);
         tv2= (TextView) selfCard.findViewById(R.id.timer_text_time);
         q19= selfCard.findViewById(R.id.item_timer_image);


        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("timer");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                endTime = dataSnapshot.child("endTime").getValue().toString();
                reconfigureClock(endTime);
                Log.v(TAG,endTime);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage(), databaseError.toException());
            }
        });
        Log.v(TAG,endTime+"chage");



        return selfCard;
    }

    public void reconfigureClock(String endTime)
    {
        try
        {backcounter.cancel();}
        catch(Exception e)
        {}

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        formatter.setLenient(false);

        long milliseconds=0;
        Date endDate;
        try {
            endDate = formatter.parse(endTime);
            milliseconds = endDate.getTime();

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();

        long diff = milliseconds - startTime;

        backcounter = new CountDownTimer(diff,1) {

            @Override
            public void onTick(long millis) {
                int days = (int) ((millis / (1000*60*60*24)));
                int seconds = (int) (millis / 1000) % 60 ;
                int minutes = (int) ((millis / (1000*60)) % 60);
                int hours   = (int) ((millis / (1000*60*60)) % 24);
                String text1 = String.format("%02d Days",days);
                tv1.setText(text1);
                String text2 = String.format("%02d:%02d:%02d",hours,minutes,seconds);
                tv2.setText(text2);
            }

            @Override
            public void onFinish() {
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                q19.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}
