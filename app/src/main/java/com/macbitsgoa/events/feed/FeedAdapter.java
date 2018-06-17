package com.macbitsgoa.events.feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.events.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for feed Recycler View.
 * @author Aayush Singla
 */

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<FeedRecyclerViewItem> viewItemList;

    FeedAdapter(final List<FeedRecyclerViewItem> viewItemList) {
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
        return new FeedViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder,
                                 final int position) {
        final FeedViewHolder viewHolder = (FeedViewHolder) holder;
        viewHolder.textOwner.setText(viewItemList.get(position).owner);
        final String likes = "liked by " + viewItemList.get(position).likes + " people";
        viewHolder.textLikes.setText(likes);
        viewHolder.imageOwner.setImageURI(viewItemList.get(position).ownerImage);
        viewHolder.imageMain.setImageURI(viewItemList.get(position).imageUrl);
        viewHolder.textDesc.setText(viewItemList.get(position).desc);
    }


}

