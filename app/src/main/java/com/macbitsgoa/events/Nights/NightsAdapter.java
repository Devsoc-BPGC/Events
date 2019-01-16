package com.macbitsgoa.events.Nights;

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

public class NightsAdapter extends RecyclerView.Adapter<NightsAdapter.ViewHolder>{

    private final Browser browser;
    private ArrayList<Nights> NightsList;
    private Context context;

    public NightsAdapter(ArrayList<com.macbitsgoa.events.Nights.Nights> list, Browser browser, Context context) {
        this.browser = browser;
        NightsList = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Fresco.initialize(context);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contacts = inflater.inflate(R.layout.item_nights_rv, parent, false);
        return new ViewHolder(contacts, browser);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fresco.initialize(context);
        holder.populate(NightsList.get(position));
    }

    @Override
    public int getItemCount() {
        return NightsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTv;
        private final TextView descTv, locationTv;
        private final SimpleDraweeView imageIv;
        private final TextView eventTimeTv;
        private final Browser browser;

        ViewHolder(final View itemView, final Browser browser) {
            super(itemView);
            this.browser = browser;
            nameTv = itemView.findViewById(R.id.item_night_name);
            imageIv = itemView.findViewById(R.id.item_night_image);
            descTv = itemView.findViewById(R.id.item_night_desc);
            locationTv = itemView.findViewById(R.id.item_night_location);
            eventTimeTv = itemView.findViewById(R.id.item_night_time);
        }

        public void populate(@NonNull final Nights nights) {
            nameTv.setText(nights.name);
            descTv.setText(nights.desc);
            locationTv.setText(nights.location);
            eventTimeTv.setText(nights.time);
            imageIv.setImageURI(nights.imageURL);
            //if (nights.onClick != null) {
            // itemView.setOnClickListener(view -> browser.launchUrl(nights.onClick));
            //}
        }

    }
}
