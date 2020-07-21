package com.vmschmidt.travelapp.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trip {

    private ArrayList<Country> countries;
    private String startDate;
    private String endDate;
    private String title;

    public Trip(String startDate, String endDate, String title){
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        countries = new ArrayList<>();
    }


    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }
}
