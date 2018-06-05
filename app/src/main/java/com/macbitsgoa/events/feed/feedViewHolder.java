package com.macbitsgoa.events.feed;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Aayush Singla
 */

public class feedViewHolder extends RecyclerView.ViewHolder {
    SimpleDraweeView imageOwner;
    SimpleDraweeView imageMain;
    TextView textOwner;
    TextView textLikes;
    TextView textDesc;

    public feedViewHolder(View itemView) {
        super(itemView);
        imageMain = itemView.findViewById(R.id.image_main);
        imageOwner = itemView.findViewById(R.id.image_owner);
        textOwner = itemView.findViewById(R.id.text_owner);
        textLikes = itemView.findViewById(R.id.text_likes);
        textDesc = itemView.findViewById(R.id.text_desc);
    }

}
