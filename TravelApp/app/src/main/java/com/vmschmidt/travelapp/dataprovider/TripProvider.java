package com.vmschmidt.travelapp.dataprovider;

import android.content.Context;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.model.Trip;
import com.vmschmidt.travelapp.support.MyCustomDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TripProvider {

    private HashMap<Integer, Trip> trips;
    private static TripProvider tripProvider;
    private static HashMap<Integer, String> stringCache;

    public enum SortingMethod {
        NEWEST(R.string.order_by_newest),
        OLDEST(R.string.order_by_oldest),
        ALPHABETICAL(R.string.order_by_alphabetical);

        private final int sortingMethod;

        SortingMethod(int sortingMethod) {
            this.sortingMethod = sortingMethod;
        }

        public int getSortingMethod(){
            return this.sortingMethod;
        }
    }

    public static String getSortingMethodString(Context context, SortingMethod sortingMethod){
        int resId = sortingMethod.getSortingMethod();

        return context.getString(resId);
    }

    private TripProvider(){
        stringCache = new HashMap<>();
        trips = new HashMap<>();
        init();
    }

    private void init(){


        ArrayList<String> countriesDummy = new ArrayList<>();
        countriesDummy.add("Bla1");
        countriesDummy.add("Bla2");
        countriesDummy.add("Bla3");
        countriesDummy.add("Bla4");

        Trip trip1 = new Trip("Trip1", countriesDummy);
        Trip trip2 = new Trip("Trip2", countriesDummy);
        Trip trip3 = new Trip("Trip3", countriesDummy);
        Trip trip4 = new Trip("Trip4", countriesDummy);
//        trips.put(trip1.getId(), trip1);
//        trips.put(trip2.getId(), trip2);
//        trips.put(trip3.getId(), trip3);
//        trips.put(trip4.getId(), trip4);
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
                        return trip1.getStartDate().toString().compareTo(trip2.getStartDate().toString());
                    }
                });
                break;

            case OLDEST:
                Collections.sort(trips, new Comparator<Trip>() {
                    @Override
                    public int compare(Trip trip1, Trip trip2) {
                        return trip2.getStartDate().toString().compareTo(trip1.getStartDate().toString());
                    }
                });
                break;

            case ALPHABETICAL:
                Collections.sort(trips, new Comparator<Trip>() {
                    @Override
                    public int compare(Trip trip1, Trip trip2) {
                        return trip1.getTitle().toLowerCase().compareTo(trip2.getTitle().toLowerCase());
                    }
                });
                break;

            default:break;
        }
        return trips;
    }

    public void addTrip(String title, ArrayList<String> countryList){
        Trip trip = new Trip(title, countryList);
        trips.put(trip.getId(), trip);
    }
}
