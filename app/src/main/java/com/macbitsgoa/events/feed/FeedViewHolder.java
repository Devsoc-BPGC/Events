package com.macbitsgoa.events.feed;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder for Feed RecyclerView.
 * @author Aayush Singla
 */

public class FeedViewHolder extends RecyclerView.ViewHolder {
    public SimpleDraweeView imageOwner;
    public SimpleDraweeView imageMain;
    public TextView textOwner;
    public TextView textLikes;
    public TextView textDesc;

    /**
     * ViewHolder for feed recycler view.
     * @param itemView view of a single layout of feed Recycler view.
     */
    public FeedViewHolder(View itemView) {
        super(itemView);
        imageMain = itemView.findViewById(R.id.image_main);
        imageOwner = itemView.findViewById(R.id.image_owner);
        textOwner = itemView.findViewById(R.id.text_owner);
        textLikes = itemView.findViewById(R.id.text_likes);
        textDesc = itemView.findViewById(R.id.text_desc);
    }

}
