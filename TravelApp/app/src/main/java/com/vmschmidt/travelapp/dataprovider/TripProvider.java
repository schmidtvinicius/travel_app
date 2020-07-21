package com.vmschmidt.travelapp.dataprovider;

import com.vmschmidt.travelapp.model.Trip;
import com.vmschmidt.travelapp.support.MyCustomDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TripProvider {

    private HashMap<String, Trip> trips;
    private static TripProvider tripProvider;

    enum SortingMethods {
        NEWEST("Newest"),
        OLDEST("Oldest"),
        ALPHABETICAL("Alphabetical");

        private final String sortingMethod;

        SortingMethods(String sortingMethod) {
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
        MyCustomDate startDate = new MyCustomDate(2000, 12, 12);
        MyCustomDate endDate = new MyCustomDate(2000, 12, 15);
        Trip trip1 = new Trip(startDate, endDate, "Trip1");
        Trip trip2 = new Trip(startDate, endDate, "Trip2");
        Trip trip3 = new Trip(startDate, endDate, "Trip3");
        Trip trip4 = new Trip(startDate, endDate, "Trip4");
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

    public void sortTrips(){

    }
}
