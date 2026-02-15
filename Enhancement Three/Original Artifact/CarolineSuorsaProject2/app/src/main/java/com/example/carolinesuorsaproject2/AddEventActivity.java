package com.example.carolinesuorsaproject2;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class AddEventActivity extends AppCompatActivity {

    private EditText editEventName, editEventDate;
    private EventDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        editEventName = findViewById(R.id.editEventName);
        editEventDate = findViewById(R.id.editEventDate);
        db = new EventDatabase(this);

        findViewById(R.id.buttonSave).setOnClickListener(v -> {
            String name = editEventName.getText().toString();
            String date = editEventDate.getText().toString();
            db.addEvent(name, date);
            finish();
        });
    }
}
