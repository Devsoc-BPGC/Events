package com.macbitsgoa.events.speakers;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

/*
 * Created by Hariram.R
 * 13/06/2018
 * */

public class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.SpeakerViewHolder> {

    private ArrayList<Speaker> SpeakerList = new ArrayList<>();

    private Context context;

    public SpeakersAdapter(ArrayList<Speaker> speakerList, Context context) {
        SpeakerList = speakerList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpeakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_speaker_details, parent, false);
        return new SpeakerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final SpeakerViewHolder holder, final int position) {
        holder.populateSpeaker(SpeakerList.get(position));
    }

    @Override
    public int getItemCount() {
        return SpeakerList.size();
    }

    public class SpeakerViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV;
        private TextView descTV;
        private SimpleDraweeView imageV;

        public SpeakerViewHolder(View itemView) {
            super(itemView);
            imageV = itemView.findViewById(R.id.speaker_image);
            nameTV = itemView.findViewById(R.id.speaker_name);
            descTV = itemView.findViewById(R.id.speaker_desc);
        }

        public void populateSpeaker(Speaker speaker) {
            nameTV.setText(speaker.Name);
            descTV.setText(speaker.Desc);
            imageV.setImageURI(speaker.imgURL);
            imageV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = speaker.clickURL;
                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);*/

                    CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
                    intentBuilder.setToolbarColor(R.color.colorPrimary);
                    intentBuilder.setSecondaryToolbarColor(R.color.colorPrimaryDark);
                    CustomTabsIntent customTabsIntent = intentBuilder.build();
                    customTabsIntent.launchUrl(context, Uri.parse(url));

                }
            });
        }
    }
}
