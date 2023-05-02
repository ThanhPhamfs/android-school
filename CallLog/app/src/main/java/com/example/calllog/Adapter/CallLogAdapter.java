package com.example.calllog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calllog.Models.CallLog;
import com.example.calllog.R;

import java.util.ArrayList;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.CallLogViewHolder>{
    Context context;
    int layoutId;
    ArrayList<CallLog> list;

    public CallLogAdapter(Context context, int layoutId, ArrayList<CallLog> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public CallLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CallLogViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogViewHolder holder, int position) {
        CallLog callLog = list.get(position);
        holder.tvCallLog.setText(callLog.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CallLogViewHolder extends RecyclerView.ViewHolder {
        TextView tvCallLog;

        public CallLogViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCallLog = (TextView) itemView.findViewById(R.id.tvShow);
        }
    }
}
