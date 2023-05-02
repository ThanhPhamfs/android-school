package com.example.calllog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calllog.Models.Contact;
import com.example.calllog.Models.SMSMessage;
import com.example.calllog.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    Context context;
    int layoutId;
    ArrayList<Contact> list;

    public ContactAdapter(Context context, int layoutId, ArrayList<Contact> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactAdapter.ContactViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = list.get(position);
        holder.tvName.setText(contact.getName());
        String numbers = "";
        for (int i = 0; i < contact.getNumbers().size(); i++) {
            String element = contact.getNumbers().get(i);
            numbers += element;
            if (i != contact.getNumbers().size() -1){
                numbers += "\n";
            }
        }
        holder.tvNumbers.setText(numbers);
        String emails = "";
        for (int i = 0; i < contact.getEmails().size(); i++) {
            String element = contact.getEmails().get(i);
            emails += element;
            if (i != contact.getEmails().size() -1){
                emails += "\n";
            }
        }
        holder.tvEmails.setText(emails);
        String address = "";
        for (int i = 0; i < contact.getAddress().size(); i++) {
            String element = contact.getAddress().get(i);
            address += element;
            if (i != contact.getAddress().size() -1){
                address += "\n";
            }
        }
        holder.tvAddresss.setText(address);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumbers, tvEmails, tvAddresss;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvNumbers = (TextView) itemView.findViewById(R.id.tvNumbers);
            tvEmails = (TextView) itemView.findViewById(R.id.tvEmails);
            tvAddresss = (TextView) itemView.findViewById(R.id.tvAddresss);
        }
    }
}
