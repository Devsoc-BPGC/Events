package com.macbitsgoa.events.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.card.MaterialCardView;
import com.macbitsgoa.events.BuildConfig;
import com.macbitsgoa.events.R;
import com.macbitsgoa.events.home.HomeCardInterface;

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



    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {
        final MaterialCardView selfCard =
                (MaterialCardView) inflater.inflate(R.layout.fragment_timer_card,
                        container, false);
        final TextView tv= (TextView) selfCard.findViewById(R.id.timer_text);


        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        formatter.setLenient(false);


        String endTime = "31.01.2019, 17:30:00";
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

        new CountDownTimer(diff,1) {

            @Override
            public void onTick(long millis) {
                int days = (int) ((millis / (1000*60*60*24)));
                int seconds = (int) (millis / 1000) % 60 ;
                int minutes = (int) ((millis / (1000*60)) % 60);
                int hours   = (int) ((millis / (1000*60*60)) % 24);
                String text = String.format("%02d Days \n %02d:%02d:%02d.%03d",days,hours,minutes,seconds,millis%1000);
                tv.setText(text);
            }

            @Override
            public void onFinish() {
                tv.setText(BuildConfig.eventName);
            }
        }.start();

        return selfCard;
    }
}
