package com.macbitsgoa.events.ProfileDetails;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.macbitsgoa.events.R;

import java.util.List;

public class ProfileFragmentAdapter extends RecyclerView.Adapter<ProfileFragmentAdapter.MyViewHolder> {


    List<ProfileModal> mData ;


    public ProfileFragmentAdapter(List<ProfileModal> mData){
        this.mData= mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qrcode,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    public List<ProfileModal> getmData() {
        return mData;
    }

    public void setmData(List<ProfileModal> mData) {
        this.mData = mData;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        holder.populate(mData.get(position));
    }

    @Override
    public int getItemCount(){
        return  mData.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView qrcode;
        private TextView positon;
        private TextView description;
        private TextView points;



        public MyViewHolder(View itemView){
            super(itemView);

            qrcode=itemView.findViewById(R.id.qrcode);
            positon=itemView.findViewById(R.id.position);
            description=itemView.findViewById(R.id.description);
            points=itemView.findViewById(R.id.points);





        }
        public  void populate( ProfileModal item)
        {
            qrcode.setText("QrCode: "+item.getQrcode());
            description.setText(" Description: "+item.getDescription());
            points.setText(String.valueOf(item.getPoints()));
            positon.setText(String.valueOf(item.getPosition()));
        }




    }}