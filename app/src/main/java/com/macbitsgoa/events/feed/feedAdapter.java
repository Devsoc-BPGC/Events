package com.macbitsgoa.events.feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.events.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Aayush Singla
 */

public class feedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<feedRecyclerViewItem> viewItemList;

    feedAdapter(final List<feedRecyclerViewItem> viewItemList) {
        this.viewItemList = viewItemList;
    }

    @Override
    public int getItemCount() {
        return viewItemList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewtype) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_feed, parent, false);
        return new feedViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final feedViewHolder viewHolder = (feedViewHolder) holder;
        viewHolder.textOwner.setText(viewItemList.get(position).getOwner());
        viewHolder.textLikes.setText("liked by " + viewItemList.get(position).getNumberLikes() + " people");
        viewHolder.imageOwner.setImageURI(viewItemList.get(position).getOwnerImage());
        viewHolder.imageMain.setImageURI(viewItemList.get(position).getImageUrl());
        viewHolder.textDesc.setText(viewItemList.get(position).getDesc());
    }


}

