package com.macbitsgoa.events.aboutfest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrganisersAdapter
        extends RecyclerView.Adapter<OrganisersAdapter.OrganiserVh> {
    private final List<Organiser> organisersList;

    public OrganisersAdapter(final List<Organiser> list) {
        organisersList = list;
    }

    @NonNull
    @Override
    public OrganiserVh onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View contacts = inflater.inflate(R.layout.vh_organisers, parent, false);
        return new OrganiserVh(contacts);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrganiserVh holder, final int position) {
        final Organiser current = organisersList.get(position);
        holder.populate(current);
    }

    @Override
    public int getItemCount() {
        return organisersList.size();
    }

    public class OrganiserVh extends RecyclerView.ViewHolder {
        OrganiserVh(final View itemView) {
            super(itemView);
        }

        /**
         * {@inheritDoc}
         */
        public void populate(final Organiser organiser) {
            ((SimpleDraweeView) itemView.findViewById(R.id.iv_avatar)).setImageURI(organiser.photo);
            ((TextView) itemView.findViewById(R.id.tv_person_name)).setText(organiser.name);
            ((TextView) itemView.findViewById(R.id.tv_post)).setText(organiser.post);
            ((TextView) itemView.findViewById(R.id.tv_phone)).setText(organiser.contact);
            ((TextView) itemView.findViewById(R.id.tv_email)).setText(organiser.email);
        }
    }
}
