package com.macbitsgoa.events.speakers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 * Created by Hariram.R
 * 13/06/2018
 * */

public class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.SpeakerViewHolder> {

    private ArrayList<String> NameList = new ArrayList<>();
    private ArrayList<String> DescList = new ArrayList<>();
    private ArrayList<String> imgList = new ArrayList<>();
    private ArrayList<String> clickList = new ArrayList<>();

    private Context context;

    public SpeakersAdapter(ArrayList<String> nameList, ArrayList<String> descList, ArrayList<String> imgList, ArrayList<String> clickList, Context context) {
        NameList = nameList;
        DescList = descList;
        this.imgList = imgList;
        this.clickList = clickList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpeakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_speaker_details, parent, false);
        SpeakerViewHolder viewHolder = new SpeakerViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final SpeakerViewHolder holder, final int position) {

        holder.imageV.setImageURI(imgList.get(position));
        holder.descTV.setText(DescList.get(position));
        holder.nameTV.setText(NameList.get(position));
        holder.imageV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = clickList.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return NameList.size();
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
    }
}
