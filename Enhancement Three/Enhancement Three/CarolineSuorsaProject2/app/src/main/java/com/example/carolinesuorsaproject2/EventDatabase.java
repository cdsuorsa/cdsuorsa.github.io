package com.example.carolinesuorsaproject2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;

/**
 * manages all SQLite operations for the events table
 * handles creation, upgrades, and CRUD operations with basic error handling
 */
public class EventDatabase extends SQLiteOpenHelper {

    // name of database
    private static final String DATABASE_NAME = "events.db";
    // current database version
    private static final int VERSION = 2; // incremented for schema change

    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // called when database is created for first time
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT, description TEXT)");
    }

    // called when database version changes
    // a proper migration strategy is recommended for official product
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("drop table if exists events");
        onCreate(db);

    }

    // insert new event into database
    public void addEvent(String name, String date, String description) {
        SQLiteDatabase db;

        // simple try-catch to handle log failures
        try {
            db = getWritableDatabase();

            // prepare key-value pairs for insertion
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("date", date);
            values.put("description", description);

            // check for failure
            long result = db.insert("events", null, values);

            if (result == -1) {
                android.util.Log.e(TAG, "Failed to insert event into database");
            }

        } catch (Exception e) {
            android.util.Log.e(TAG, "Error inserting event", e);
        }

    }

    // retrieve all events from database
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db;
        Cursor cursor = null;

        try {
            db = getReadableDatabase();

            // query all rows from the events table
            cursor = db.rawQuery("SELECT * FROM events", null);

            // iterate through results and build event objects
            while (cursor.moveToNext()) {
                events.add(new Event(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)
                ));
            }

        } catch (Exception e) {
            android.util.Log.e(TAG, "Error reading events", e);
        } finally {
            if (cursor != null) cursor.close();
        }

        return events;
    }

    // delete an event by id
    public void deleteEvent(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("events", "id=?", new String[]{String.valueOf(id)});
    }

    // update an existing event's name, date, and description
    public void updateEvent(int id, String name, String date, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date", date);
        values.put("description", description);
        db.update("events", values, "id=?", new String[]{String.valueOf(id)});
    }
}
