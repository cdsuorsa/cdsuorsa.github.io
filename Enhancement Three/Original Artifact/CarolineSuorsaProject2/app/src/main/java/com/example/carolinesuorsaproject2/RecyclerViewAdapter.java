package com.example.carolinesuorsaproject2;

import android.content.Context;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

    private List<Event> events;
    private EventDatabase db;
    private boolean smsEnabled;



    public RecyclerViewAdapter(List<Event> events, EventDatabase db, boolean smsEnabled) {
        this.events = events;
        this.db = db;
        this.smsEnabled = smsEnabled;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.textEvent.setText(event.getName());
        holder.textDate.setText(event.getDate());

        holder.buttonDelete.setOnClickListener(v -> {
            db.deleteEvent(event.getId());
            updateData(db.getAllEvents());
        });

        if (smsEnabled) {
            sendSMSAlert(event.getName(), event.getDate(), holder.itemView.getContext());
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void updateData(List<Event> newEvents) {
        this.events = newEvents;
        notifyDataSetChanged();
    }


    private String phoneNumber = "15555215554"; // number of emulator
    private void sendSMSAlert(String name, String date, Context context) {
        String message = "Reminder: " + name + " is on " + date;

        SmsManager smsManager = context.getSystemService(SmsManager.class);
        if (smsManager != null) {
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        }
    }
}

