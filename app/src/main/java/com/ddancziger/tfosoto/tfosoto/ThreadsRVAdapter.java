package com.ddancziger.tfosoto.tfosoto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DanielDancziger on 4/2/2016.
 */
public class ThreadsRVAdapter extends RecyclerView.Adapter<ThreadsRVAdapter.ThreadsRVViewHolder> {



    public interface OnItemClickListener{
        void onItemClick(Map.Entry<Long,List<DataSMS>> item);
    }

    private List<Map.Entry<Long ,List<DataSMS>>> threads;
    private OnItemClickListener listener;

    public static class ThreadsRVViewHolder extends RecyclerView.ViewHolder {

        public TextView mLastMessage;
        public TextView mPhoneTime;

        public ThreadsRVViewHolder(View v) {
            super(v);
            mLastMessage = (TextView) v.findViewById(R.id.lastMessage);
            mPhoneTime = (TextView) v.findViewById(R.id.smsTime);
        }

        public void bind(final Map.Entry<Long, List<DataSMS>> item, final OnItemClickListener listener){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public ThreadsRVAdapter(List<Map.Entry<Long, List<DataSMS>>> threads, OnItemClickListener listener) {
        this.threads = threads;
        this.listener = listener;
    }

    @Override
    public ThreadsRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_thread_row, parent, false);
        return new ThreadsRVViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ThreadsRVViewHolder holder, int position) {
        final Map.Entry<Long,List<DataSMS>> lastEntry =  threads.get(position);
        String body = lastEntry.getValue().get(0).getmBody();
        if(body.length() > 50)
            body = body.substring(0,50) + "...";
        holder.mLastMessage.setText(body);
        holder.mPhoneTime.setText(lastEntry.getValue().get(0).getmAddress() + " - " + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(lastEntry.getValue().get(0).getmDate()));
        holder.bind(lastEntry, listener);
    }


    @Override
    public int getItemCount() {
        return threads.size();
    }
}
