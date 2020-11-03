package com.vmschmidt.travelapp.support;

public class MyCustomDate {

    private String year;
    private String month;
    private String day;

    public MyCustomDate(String year, String month, String day){

        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyCustomDate(String date){
        String[] splitDate = date.split("[-/.]");

        this.day = splitDate[0];
        this.month = splitDate[1];
        this.year = splitDate[2];
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public boolean isBefore(MyCustomDate otherDate){

        if(this.year.compareTo(otherDate.getYear()) > 0){
            return false;
        }
        if(this.year.compareTo(otherDate.getYear()) < 0){
            return true;
        }
        if(this.month.compareTo(otherDate.getMonth()) > 0){
            return false;
        }
        if(this.month.compareTo(otherDate.getMonth()) < 0){
            return true;
        }
        if(this.day.compareTo(otherDate.day) > 0){
            return false;
        }
        return this.day.compareTo(otherDate.day) < 0;
    }

    @Override
    public String toString() {
        return day + "-" + month + "-" + year;
    }
}
