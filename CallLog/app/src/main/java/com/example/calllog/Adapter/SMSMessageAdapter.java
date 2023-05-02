package com.example.calllog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calllog.Models.CallLog;
import com.example.calllog.Models.SMSMessage;
import com.example.calllog.R;

import java.util.ArrayList;

public class SMSMessageAdapter extends RecyclerView.Adapter<SMSMessageAdapter.SMSMessageHolder> {
    Context context;
    int layoutId;
    ArrayList<SMSMessage> list;

    public SMSMessageAdapter(Context context, int layoutId, ArrayList<SMSMessage> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public SMSMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SMSMessageAdapter.SMSMessageHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SMSMessageHolder holder, int position) {
        SMSMessage smsMessage = list.get(position);
        holder.tvCallLog.setText(smsMessage.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SMSMessageHolder extends RecyclerView.ViewHolder {
        TextView tvCallLog;

        public SMSMessageHolder(@NonNull View itemView) {
            super(itemView);
            tvCallLog = (TextView) itemView.findViewById(R.id.tvShow);
        }
    }
}
