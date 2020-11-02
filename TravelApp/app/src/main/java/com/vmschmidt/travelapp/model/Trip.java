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
    private static int lastDate = 1;

    public Trip(String title, int id, Bitmap icon){
        this.title = title;
        this.id = id;
        this.icon = icon;
        setUpDate();
    }

    public Trip(String title, ArrayList<Country> countries, Bitmap icon, int id){
        this.icon = icon;
        setUpDate();
        this.title = title;
        this.countries = countries;
        this.id = id;
    }

    private void setUpDate(){
        this.startDate = new MyCustomDate(2000, 12, lastDate);
        lastDate++;
        this.endDate = new MyCustomDate(2000, 12, lastDate);
        lastDate++;
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
}
