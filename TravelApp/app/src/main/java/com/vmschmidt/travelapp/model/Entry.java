package com.vmschmidt.travelapp.model;

import com.vmschmidt.travelapp.support.MyCustomDate;

public class Entry {

    private int id;
    private int tripId;
    private MyCustomDate date;
    private String content;

    public Entry(int id, int tripId, MyCustomDate date, String content){
        this.id = id;
        this.tripId = tripId;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getTripId() {
        return tripId;
    }

    public MyCustomDate getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}
