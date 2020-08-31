package com.vmschmidt.travelapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.database.MyCustomOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Model {

    private DateFormat dateFormatter;
    private SQLiteDatabase database;
    private MyCustomOpenHelper customOpenHelper;
    private ArrayList<Country> countries;

    private static Model instance;

    private Model(){
        countries = new ArrayList<>();
    }

    public static Model getInstance(){
        if(instance == null){
            instance = new Model();
        }
        return instance;
    }

    public void initializeDatabase(Context context){
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        customOpenHelper = new MyCustomOpenHelper(context);
        database = customOpenHelper.getWritableDatabase();
    }

    public ArrayList<Country> getCountries(){


        if(countries.isEmpty()){
            Cursor cursor = database.rawQuery("SELECT * FROM Country", null);
            if(cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    String code = cursor.getString(cursor.getColumnIndex("code"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    Country country = new Country(code, name);
                    countries.add(country);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                return country1.getName().compareToIgnoreCase(country2.getName());
            }
        });
        return countries;
    }

    public ArrayList<Country> getCountriesFromIndexes(int[] selectedIndexes){
        ArrayList<Country> selectedCountries = new ArrayList<>();

        for(int index : selectedIndexes){
            selectedCountries.add(countries.get(index));
        }

        return selectedCountries;
    }

    public void addTrip(String name, ArrayList<Country> selectedCountries, byte[] tripIconByteArray){

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("icon", tripIconByteArray);
        database.insert("Trip", null, contentValues);

        Cursor cursor = database.rawQuery("SELECT id FROM Trip ORDER BY id DESC LIMIT 1", null);
        int lastId;
        if(cursor.moveToFirst()){
            lastId = cursor.getInt(cursor.getColumnIndex("id"));
            for(Country country : selectedCountries){
                database.execSQL("INSERT INTO Trip_country (country_code, trip_id) VALUES " +
                        "(?, ?)", new String[]{country.getCode(), String.valueOf(lastId)});
                Log.d("SQLITE", "Inserted " + country.getCode() + " and " + lastId);
            }
        }
        cursor.close();
    }

    public ArrayList<Trip> getTrips(){
        ArrayList<Trip> trips = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM Trip", null);
            if(cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    int tripId = cursor.getInt(cursor.getColumnIndex("id"));
                    String tripName = cursor.getString(cursor.getColumnIndex("name"));
                    byte[] tripIconByteArray = cursor.getBlob(cursor.getColumnIndex("icon"));
                    Bitmap tripIconBitmap = BitmapFactory.decodeByteArray(tripIconByteArray, 0, tripIconByteArray.length);
                    Trip trip = new Trip(tripName, tripId, tripIconBitmap);
                    trips.add(trip);
                }
            }
            cursor.close();
        return trips;
    }

//    public ArrayList<Trip> getTrips(){
//        ArrayList<Trip> trips = new ArrayList<Trip>();
//
//        Cursor cursorTrip = database.rawQuery("SELECT t.id AS trip_id, t.name AS trip_name, t.icon AS trip_icon, tc.country_code, c.name AS country_name\n" +
//                "FROM Trip t \n" +
//                "LEFT OUTER JOIN Trip_country tc\n" +
//                "ON tc.trip_id = t.id\n" +
//                "LEFT OUTER JOIN Country c\n" +
//                "ON c.code = tc.country_code\n" +
//                "ORDER BY t.id", null);
//        if(cursorTrip.moveToFirst()){
//            int tripId = cursorTrip.getInt(cursorTrip.getColumnIndex("trip_id"));
//            while(!cursorTrip.isAfterLast()){
//                int lastId = tripId;
//                String tripName = cursorTrip.getString(cursorTrip.getColumnIndex("trip_name"));
//                byte[] tripIconByteArray = cursorTrip.getBlob(cursorTrip.getColumnIndex("trip_icon"));
//                Bitmap tripIconBitmap;
//                tripIconBitmap = BitmapFactory.decodeByteArray(tripIconByteArray, 0, tripIconByteArray.length);
//                ArrayList<Country> countries = new ArrayList<>();
//                while(lastId == tripId && !cursorTrip.isAfterLast()){
//                    String countryName = cursorTrip.getString(cursorTrip.getColumnIndex("country_name"));
//                    String countryCode = cursorTrip.getString(cursorTrip.getColumnIndex("country_code"));
//                    Country country = new Country(countryCode, countryName);
//                    countries.add(country);
//                    cursorTrip.moveToNext();
//                    if(!cursorTrip.isAfterLast()){
//                        tripId = cursorTrip.getInt(cursorTrip.getColumnIndex("trip_id"));
//                    }
//                }
//                Trip trip = new Trip(tripName, countries, tripIconBitmap, lastId);
//                trips.add(trip);
//            }
//        }
//        cursorTrip.close();
//        return trips;
//    }

    public ArrayList<String> getYears(){
        ArrayList<String> years = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT DISTINCT strftime('%Y', date) AS year FROM Entry", null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String year = cursor.getString(cursor.getColumnIndex("year"));
                years.add(year);
            }
        }
        cursor.close();

        return years;
    }

    public ArrayList<Trip> getTripsFromYear(String selectedYear){

        ArrayList<Trip> matchingTrips = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT t.id AS trip_id, t.name AS trip_name, t.icon AS trip_icon\n" +
                "FROM Entry e\n" +
                "INNER JOIN Trip t\n" +
                "ON e.trip_id = t.id\n" +
                "WHERE strftime('%Y', date) = ?\n" +
                "GROUP BY trip_id", new String[]{selectedYear});
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String tripName = cursor.getString(cursor.getColumnIndex("trip_name"));
                int tripId = cursor.getInt(cursor.getColumnIndex("trip_id"));
                byte[] tripIconByteArray = cursor.getBlob(cursor.getColumnIndex("trip_icon"));
                Bitmap tripIconBitmap = BitmapFactory.decodeByteArray(tripIconByteArray, 0, tripIconByteArray.length);
                Trip trip = new Trip(tripName, tripId, tripIconBitmap);
                matchingTrips.add(trip);
            }
        }
        cursor.close();
        return matchingTrips;
    }


}
