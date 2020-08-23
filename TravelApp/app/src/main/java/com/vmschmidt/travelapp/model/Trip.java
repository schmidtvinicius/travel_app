package com.vmschmidt.travelapp.model;

import com.vmschmidt.travelapp.support.MyCustomDate;

import java.util.ArrayList;

public class Trip {

    private ArrayList<String> countries;
    private MyCustomDate startDate;
    private MyCustomDate endDate;
    private String title;
    private int id;
    private static int count;
    private static int lastDate = 1;

    public Trip(String title, ArrayList<String> countries){
        setUpDate();
        this.title = title;
        id = count;
        count++;
        this.countries = countries;
    }

    private void setUpDate(){
        this.startDate = new MyCustomDate(2000, 12, lastDate);
        lastDate++;
        this.endDate = new MyCustomDate(2000, 12, lastDate);
        lastDate++;
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
