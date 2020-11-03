package com.vmschmidt.travelapp.model;

import android.graphics.Bitmap;

import com.vmschmidt.travelapp.support.MyCustomDate;

import java.util.ArrayList;

public class Trip {

    private ArrayList<Country> countries;
    private MyCustomDate startDate;
    private MyCustomDate endDate;
    private String title;
    private Bitmap icon;
    private int id;

    public Trip(String title, MyCustomDate startDate, MyCustomDate endDate, int id, Bitmap icon){
        this.title = title;
        this.id = id;
        this.icon = icon;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Trip(String title, MyCustomDate startDate, MyCustomDate endDate, int id, Bitmap icon, ArrayList<Country> countries ){
        this(title, startDate, endDate, id, icon);
        this.countries = countries;
    }

    public Bitmap getIcon(){
        return this.icon;
    }


    public MyCustomDate getStartDate() {
        return startDate;
    }

    public MyCustomDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "countries=" + countries +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", icon=" + icon +
                ", id=" + id +
                '}';
    }
}
