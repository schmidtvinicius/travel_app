package com.vmschmidt.travelapp.model;

import com.vmschmidt.travelapp.support.MyCustomDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Trip {

    private ArrayList<Country> countries;
    private MyCustomDate startDate;
    private MyCustomDate endDate;
    private String title;

    public Trip(MyCustomDate startDate, MyCustomDate endDate, String title){
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        countries = new ArrayList<>();
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
}
