package com.macbitsgoa.events.sponsors;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.macbitsgoa.events.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder> {

    private List<SponsorItem> sponsorItems;
    private Context context;

    public SponsorAdapter(List<SponsorItem> sponsorItems, Context context) {
        this.sponsorItems = sponsorItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SponsorItem sponsorItem=sponsorItems.get(position);
        holder.textViewsponsorName.setText(sponsorItem.getSponsorName());
         Picasso.get()
                .load(sponsorItem.getSponsorLogo())
                .into(holder.imageViewsponsorImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CustomTabsIntent intent=new CustomTabsIntent.Builder().build();
                final String launchUrl=sponsorItem.getSponsorClickUrl();
                intent.launchUrl(context, Uri.parse(launchUrl));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sponsorItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewsponsorName;
        public ImageView imageViewsponsorImage;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.sponsoritemcardview);
            textViewsponsorName=itemView.findViewById(R.id.sponsoritemname);
            imageViewsponsorImage=itemView.findViewById(R.id.sponsoritemlogo);
        }
    }
}
