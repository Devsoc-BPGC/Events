package com.macbitsgoa.events.aboutmac;

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

public class DevelopersAdapter
        extends RecyclerView.Adapter<DevelopersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DevelopersList> developersList = new ArrayList<DevelopersList>();
    private TextView contact;
    private TextView email;


    public DevelopersAdapter(ArrayList<DevelopersList> list, Context context) {
        this.context = context;
        developersList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contacts = inflater.inflate(R.layout.item_developers_rv, parent, false);
        return new ViewHolder(contacts);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DevelopersList current = developersList.get(position);
        holder.setPhoto(current.getPhoto());
        holder.setName(current.getName());
        holder.setContact(current.getContact());
        holder.setEmail(current.getEmail());
        holder.handleClick(context);
    }

    @Override
    public int getItemCount() {
        return developersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(final View itemView) {
            super(itemView);
        }

        private void handleClick(final Context context) {

            contact = itemView.findViewById(R.id.developers_contact);
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + contact.getText()));
                    context.startActivity(intent);
                }
            });

            email = itemView.findViewById(R.id.developers_email);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.ACTION_SENDTO, email.getText());
                    Intent mailer = Intent.createChooser(intent, "Choose your E-email application");
                    context.startActivity(mailer);
                }
            });

        }

        private void setPhoto(String url) {
            final SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.developers_photo);
            Uri imageUri = Uri.parse(url);
            simpleDraweeView.setImageURI(imageUri);
        }

        private void setName(String name) {
            TextView textView = (TextView) itemView.findViewById(R.id.developers_name);
            textView.setText(name);
        }

        private void setContact(String contact) {
            TextView textView = (TextView) itemView.findViewById(R.id.developers_contact);
            textView.setText(contact);
        }

        private void setEmail(String email) {
            TextView textView = (TextView) itemView.findViewById(R.id.developers_email);
            textView.setText(email);
        }
    }
}

