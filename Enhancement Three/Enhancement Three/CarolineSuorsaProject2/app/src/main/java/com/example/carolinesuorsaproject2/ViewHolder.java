package com.example.carolinesuorsaproject2;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textEvent, textDate, textDescription;
    Button buttonDelete;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        // connect fields to the views defined in the XML layout
        textEvent = itemView.findViewById(R.id.textEvent);
        textDate = itemView.findViewById(R.id.textDate);
        textDescription = itemView.findViewById(R.id.textDescription);
        buttonDelete = itemView.findViewById(R.id.button_delete);

    }
}
