package com.vmschmidt.travelapp.dataprovider;

import com.vmschmidt.travelapp.model.Trip;
import com.vmschmidt.travelapp.support.MyCustomDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TripProvider {

    private HashMap<String, Trip> trips;
    private static TripProvider tripProvider;

    public enum SortingMethod {
        NEWEST("Newest"),
        OLDEST("Oldest"),
        ALPHABETICAL("Alphabetical");

        private final String sortingMethod;

        SortingMethod(String sortingMethod) {
            this.sortingMethod = sortingMethod;
        }

        String getSortingMethod(){
            return sortingMethod;
        }
    }

    private TripProvider(){
        trips = new HashMap<>();
        init();
    }

    private void init(){
        MyCustomDate startDate1 = new MyCustomDate(2000, 12, 12);
        MyCustomDate startDate2 = new MyCustomDate(2001, 12, 12);
        MyCustomDate startDate3 = new MyCustomDate(2002, 12, 12);
        MyCustomDate startDate4 = new MyCustomDate(2003, 12, 12);
        MyCustomDate endDate1 = new MyCustomDate(2000, 12, 15);
        MyCustomDate endDate2 = new MyCustomDate(2001, 12, 15);
        MyCustomDate endDate3 = new MyCustomDate(2002, 12, 15);
        MyCustomDate endDate4 = new MyCustomDate(2003, 12, 15);
        Trip trip1 = new Trip(startDate1, endDate1, "Trip1");
        Trip trip2 = new Trip(startDate2, endDate2, "Trip2");
        Trip trip3 = new Trip(startDate3, endDate3, "Trip3");
        Trip trip4 = new Trip(startDate4, endDate4, "Trip4");
        trips.put(trip1.getTitle(), trip1);
        trips.put(trip2.getTitle(), trip2);
        trips.put(trip3.getTitle(), trip3);
        trips.put(trip4.getTitle(), trip4);
    }

    public static TripProvider getInstance(){

        if(tripProvider == null){
            tripProvider = new TripProvider();
        }
        return tripProvider;
    }

    public ArrayList<Trip> getAllTrips(){

        Collection<Trip> tripsCollection = trips.values();
        return new ArrayList<>(tripsCollection);
    }

    public ArrayList<Trip> getTripsFromYear(int year){

        ArrayList<Trip> matchingTrips = new ArrayList<>();
        for(Trip trip : trips.values()){
            if(trip.getStartDate().getYear() == year){
                matchingTrips.add(trip);
            }
        }

        return matchingTrips;
    }

    public ArrayList<Trip> sortTrips(ArrayList<Trip> trips, SortingMethod sortingMethod){
        switch (sortingMethod){

            case NEWEST:
                Collections.sort(trips, new Comparator<Trip>() {
                    @Override
                    public int compare(Trip trip1, Trip trip2) {
                        return trip2.getStartDate().toString().compareTo(trip1.getStartDate().toString());
                    }
                });
                break;

            case OLDEST:
                Collections.sort(trips, new Comparator<Trip>() {
                    @Override
                    public int compare(Trip trip1, Trip trip2) {
                        return trip1.getStartDate().toString().compareTo(trip2.getStartDate().toString());
                    }
                });
                break;

            case ALPHABETICAL:
                Collections.sort(trips, new Comparator<Trip>() {
                    @Override
                    public int compare(Trip trip1, Trip trip2) {
                        return trip1.getTitle().compareTo(trip2.getTitle());
                    }
                });
                break;

            default:break;
        }
        return trips;
    }
}
