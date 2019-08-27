package com.macbitsgoa.events.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.macbitsgoa.events.R;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {


    List<Profile> mData ;


    public ProfileAdapter( List<Profile> mData){
        this.mData= mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qrcode,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        holder.qcode.setText(mData.get(position).getQrcode());
      }

    @Override
    public int getItemCount(){
        return  mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView qcode;


        public MyViewHolder(View itemView){
            super(itemView);

            qcode=(TextView)itemView.findViewById(R.id.qrcode);



        }

    }}
