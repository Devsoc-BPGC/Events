package com.macbitsgoa.events.speakers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.Browser;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.ViewHolder>
{
    private final Browser browser;
    private Context context;
    private ArrayList<com.macbitsgoa.events.speakers.Speakers> speakersList;

    public SpeakersAdapter(ArrayList<com.macbitsgoa.events.speakers.Speakers> list, Browser browser, Context context) {
        this.browser = browser;
        speakersList = list;
        this.context = context;

    }

    @Override
    public SpeakersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contacts = inflater.inflate(R.layout.item_speakers_rv, parent, false);
        return new SpeakersAdapter.ViewHolder(contacts, browser);

    }

    @Override
    public void onBindViewHolder(SpeakersAdapter.ViewHolder holder, int position) {
        Fresco.initialize(context);
        holder.populate(speakersList.get(position));
    }

    @Override
    public int getItemCount() {
        return speakersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTv;
        private final TextView descTv;
        private final SimpleDraweeView speakerIv;
        private final TextView designationTv;
        private final Browser browser;

        ViewHolder(final View itemView, final Browser browser) {
            super(itemView);
            this.browser = browser;
            nameTv = itemView.findViewById(R.id.item_speaker_title);
            speakerIv = itemView.findViewById(R.id.item_speaker_image);
            descTv = itemView.findViewById(R.id.item_speaker_desc);
            designationTv = itemView.findViewById(R.id.item_speaker_designation);
        }

        public void populate(@NonNull final com.macbitsgoa.events.speakers.Speakers speakers) {
            nameTv.setText(speakers.name);
            descTv.setText(speakers.desc);
            designationTv.setText(speakers.designation);
            speakerIv.setImageURI(speakers.image);
            if (speakers.onClick != null) {
                itemView.setOnClickListener(view -> browser.launchUrl(speakers.onClick));
            }
        }

    }
}
