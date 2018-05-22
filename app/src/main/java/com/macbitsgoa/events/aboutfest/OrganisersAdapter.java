package com.macbitsgoa.events.aboutfest;

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

import androidx.recyclerview.widget.RecyclerView;

public class OrganisersAdapter extends RecyclerView.Adapter<OrganisersAdapter.OrganisersViewHolder>{
    private Context context;
    private ArrayList<OrganisersList> organisersList = new ArrayList<OrganisersList>();
    private TextView contact;
    private TextView email;


    public OrganisersAdapter(ArrayList<OrganisersList> list, Context context) {
        this.context = context;
        organisersList = list;
    }

    @Override
    public OrganisersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contacts = inflater.inflate(R.layout.item_organisers_rv, parent, false);
        return new OrganisersViewHolder(contacts);
    }

    @Override
    public void onBindViewHolder(OrganisersViewHolder holder, int position) {
        OrganisersList current = organisersList.get(position);
        holder.setPhoto(current.getPhoto());
        holder.setName(current.getName());
        holder.setPost(current.getPost());
        holder.setContact(current.getContact());
        holder.setEmail(current.getEmail());
        holder.handleClick(context);
    }

    @Override
    public int getItemCount() {
        return organisersList.size();
    }

    public class OrganisersViewHolder extends RecyclerView.ViewHolder {
        OrganisersViewHolder(final View itemView) {
            super(itemView);
        }

        void handleClick(final Context context) {

            contact = itemView.findViewById(R.id.contact);
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + contact.getText()));
                    context.startActivity(intent);
                }
            });

            email = itemView.findViewById(R.id.email);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.ACTION_SENDTO, email.getText());
                    Intent emailer = Intent.createChooser(intent, "Choose your E-email application");
                    context.startActivity(emailer);
                }
            });

        }

        void setPhoto(String url) {
            final SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.photo);
            Uri imageUri = Uri.parse(url);
            simpleDraweeView.setImageURI(imageUri);
        }

        void setName(String name) {
            TextView textView = (TextView) itemView.findViewById(R.id.name);
            textView.setText(name);
        }

        void setPost(String post) {
            TextView textView = (TextView) itemView.findViewById(R.id.post);
            textView.setText(post);
        }

        void setContact(String contact) {
            TextView textView = (TextView) itemView.findViewById(R.id.contact);
            textView.setText(contact);
        }

        void setEmail(String email) {
            TextView textView = (TextView) itemView.findViewById(R.id.email);
            textView.setText(email);
        }
    }
}
