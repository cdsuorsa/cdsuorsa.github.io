package com.example.carolinesuorsaproject2;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textEvent, textDate;
    Button buttonDelete;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textEvent = itemView.findViewById(R.id.textEvent);
        textDate = itemView.findViewById(R.id.textDate);
        buttonDelete = itemView.findViewById(R.id.button_delete);
    }
}
