package com.macbitsgoa.events.festMerch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.Browser;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class festMerchAdapter extends RecyclerView.Adapter<festMerchAdapter.ViewHolder> {

    private Context context;
    private final Browser browser;
    private ArrayList<Merchandise> MerchandiseList;

    public festMerchAdapter(ArrayList<Merchandise> list, Context context, Browser browser) {
        this.context = context;
        this.browser = browser;
        MerchandiseList = list;
    }

    @Override
    public festMerchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contacts = inflater.inflate(R.layout.item_merchandise_rv, parent, false);
        return new festMerchAdapter.ViewHolder(contacts, browser);

    }

    @Override
    public void onBindViewHolder(festMerchAdapter.ViewHolder holder, int position) {
        holder.populate(MerchandiseList.get(position));
    }

    @Override
    public int getItemCount() {
        return MerchandiseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTv;
        private final TextView descTv;
        private final SimpleDraweeView merchIv;
        private final TextView priceTv;
        private final Browser browser;

        ViewHolder(final View itemView, final Browser browser) {
            super(itemView);
            this.browser = browser;
            nameTv = itemView.findViewById(R.id.item_merch_title);
            merchIv = itemView.findViewById(R.id.item_merch_image);
            descTv = itemView.findViewById(R.id.item_merch_desc);
            priceTv = itemView.findViewById(R.id.item_merch_price);
        }

        public void populate(@NonNull final Merchandise merchandise) {
            nameTv.setText(merchandise.name);
            descTv.setText(merchandise.desc);
            priceTv.setText(merchandise.price);
            merchIv.setImageURI(merchandise.image);
            if (merchandise.onClick != null) {
                itemView.setOnClickListener(view -> browser.launchUrl(merchandise.onClick));
            }
        }

    }
}
