package com.macbitsgoa.events.eateries;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.events.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class EateriesAdapter
        extends RecyclerView.Adapter<EateriesAdapter.ViewHolder> {
    private ArrayList<EateriesList> eateriesList = new ArrayList<>();

    public EateriesAdapter(ArrayList<EateriesList> list) {
        eateriesList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View eateries = layoutInflater.inflate(R.layout.item_eateries, parent, false);
        return new ViewHolder(eateries);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EateriesList current = eateriesList.get(position);
        holder.setPic(current.getPic());
        holder.setName(current.getName());
        holder.setLoc(current.getLocation());
        holder.setDesc(current.getDesc());
    }

    @Override
    public int getItemCount() {
        return eateriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(final View itemView) {
            super(itemView);
        }

        private void setPic(String pic) {
            final SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.eateries_pic);
            Uri imageUri = Uri.parse(pic);
            simpleDraweeView.setImageURI(imageUri);
        }

        private void setName(String name) {
            TextView textView = (TextView) itemView.findViewById(R.id.eateries_name);
            textView.setText(name);
        }

        private void setDesc(String desc) {
            TextView textView = (TextView) itemView.findViewById(R.id.eateries_desc);
            textView.setText(desc);
        }

        private void setLoc(String loc) {
            TextView textView = (TextView) itemView.findViewById(R.id.eateries_loc);
            textView.setText(loc);
        }
    }
}
