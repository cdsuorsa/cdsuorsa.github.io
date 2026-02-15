package com.example.carolinesuorsaproject2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;

public class EventDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "events.db";
    private static final int VERSION = 1;

    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("drop table if exists events");
        onCreate(db);
    }

    public void addEvent(String name, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date", date);
        db.insert("events", null, values);
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM events", null);
        while (cursor.moveToNext()) {
            events.add(new Event(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2)));
        }
        cursor.close();
        return events;
    }

    public void deleteEvent(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("events", "id=?", new String[]{String.valueOf(id)});
    }

    public void updateEvent(int id, String name, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date", date);
        db.update("events", values, "id=?", new String[]{String.valueOf(id)});
    }
}
