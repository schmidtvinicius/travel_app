package com.vmschmidt.travelapp.support;

public class MyCustomDate {

    private int year;
    private int month;
    private int day;

    public MyCustomDate(int year, int month, int day){

        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyCustomDate(String date){
        String[] splitDate = date.split("[/.-]");

        this.day = Integer.parseInt(splitDate[0]);
        this.month = Integer.parseInt(splitDate[1]);
        this.year = Integer.parseInt(splitDate[2]);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public boolean isBefore(MyCustomDate otherDate){

        if(this.year > otherDate.year){
            return false;
        }
        if(this.year < otherDate.year){
            return true;
        }
        if(this.month > otherDate.month){
            return false;
        }
        if(this.month < otherDate.month){
            return true;
        }
        if(this.day > otherDate.day){
            return false;
        }
        return this.day < otherDate.day;
    }

    @Override
    public String toString() {
        return day + "-" + month + "-" + year;
    }
}
