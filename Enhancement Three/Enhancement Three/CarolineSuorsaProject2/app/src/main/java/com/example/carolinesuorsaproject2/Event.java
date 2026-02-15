package com.example.carolinesuorsaproject2;

public class Event {
    private int id;
    private String name;
    private String date;
    private String description;

    // constructor method
    public Event(int id, String name, String date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }

    // getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

}
