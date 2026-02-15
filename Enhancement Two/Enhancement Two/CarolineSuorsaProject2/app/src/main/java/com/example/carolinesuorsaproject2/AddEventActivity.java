package com.example.carolinesuorsaproject2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.DatePicker;
import java.util.Calendar;


public class AddEventActivity extends AppCompatActivity {

    private EditText editEventName, editEventDate;
    private EventDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        // connect DatePicker and UI fields for event name and date from layout
        DatePicker datePicker = findViewById(R.id.datePicker);
        editEventName = findViewById(R.id.editEventName);
        editEventDate = findViewById(R.id.editEventDate);
        // initialize database helper
        db = new EventDatabase(this);

        // get today's date using calendar instance
        Calendar today = Calendar.getInstance();

        // initialize DatePicker with the current date
        datePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),

                // datePicker listener
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int month, int day) {

                        // month is 0 based so add 1
                        // format string to yyyy-mm-dd
                        String formatted = String.format("%04d-%02d-%02d", year, month + 1, day);

                        // update the text box
                        editEventDate.setText(formatted);

                        // hide the DatePicker after selection
                        view.setVisibility(View.GONE);

                    }
                }
        );

        // show DatePicker when the date field is clicked
        editEventDate.setOnClickListener(v -> {
            datePicker.setVisibility(View.VISIBLE);
        });


        // save button click listener
        findViewById(R.id.buttonSave).setOnClickListener(v -> {
            String name = editEventName.getText().toString();
            String date = editEventDate.getText().toString();
            db.addEvent(name, date);
            finish();
        });
    }
}
