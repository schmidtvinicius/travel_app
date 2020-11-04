package com.vmschmidt.travelapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.vmschmidt.travelapp.MainActivity;
import com.vmschmidt.travelapp.database.MyCustomOpenHelper;
import com.vmschmidt.travelapp.support.MyCustomDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Timer;

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

    public void addTrip(String name, MyCustomDate startDate, MyCustomDate endDate, ArrayList<Country> selectedCountries, byte[] tripIconByteArray){

        ContentValues contentValues = new ContentValues();

        contentValues.put("start_date", startDate.toString());
        contentValues.put("end_date", endDate.toString());
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
                    String startDateString = cursor.getString(cursor.getColumnIndex("start_date"));
                    String endDateString = cursor.getString(cursor.getColumnIndex("end_date"));
                    Log.d("START", startDateString);
                    Log.d("END", endDateString);
                    int tripId = cursor.getInt(cursor.getColumnIndex("id"));
                    String tripName = cursor.getString(cursor.getColumnIndex("name"));
                    byte[] tripIconByteArray = cursor.getBlob(cursor.getColumnIndex("icon"));
                    MyCustomDate startDate = new MyCustomDate(cursor.getString(cursor.getColumnIndex("start_date")));
                    MyCustomDate endDate = new MyCustomDate(cursor.getString(cursor.getColumnIndex("end_date")));
                    Bitmap tripIconBitmap = BitmapFactory.decodeByteArray(tripIconByteArray, 0, tripIconByteArray.length);
                    Trip trip = new Trip(tripName, startDate, endDate, tripId, tripIconBitmap);
                    trips.add(trip);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        return trips;
    }

    public Trip getTripById(int tripId){

        Cursor tripCursor = database.rawQuery("SELECT tc.trip_id, t.name AS trip_name, t.start_date, t.end_date, t.icon, c.name AS country_name, tc.country_code\n" +
                "FROM Trip_country tc\n" +
                "INNER JOIN Country c\n" +
                "ON c.code = tc.country_code\n" +
                "INNER JOIN Trip t\n" +
                "ON t.id = tc.trip_id\n" +
                "WHERE trip_id = ?", new String[]{String.valueOf(tripId)});

        Trip trip = null;

        if(tripCursor.moveToFirst()){
            ArrayList<Country> countries = new ArrayList<>();
            String name = tripCursor.getString(tripCursor.getColumnIndex("trip_name"));
            MyCustomDate startDate = new MyCustomDate(tripCursor.getString(tripCursor.getColumnIndex("start_date")));
            MyCustomDate endDate = new MyCustomDate(tripCursor.getString(tripCursor.getColumnIndex("end_date")));
            byte[] tripIconByteArray = tripCursor.getBlob(tripCursor.getColumnIndex("icon"));
            Bitmap tripIconBitmap = BitmapFactory.decodeByteArray(tripIconByteArray, 0, tripIconByteArray.length);
            while(!tripCursor.isAfterLast()){
                String countryName = tripCursor.getString(tripCursor.getColumnIndex("country_name"));
                String countryCode = tripCursor.getString(tripCursor.getColumnIndex("country_code"));
                Country country = new Country(countryCode, countryName);
                countries.add(country);
                tripCursor.moveToNext();
            }
            trip = new Trip(name, startDate, endDate, tripId, tripIconBitmap, countries);
        }
        tripCursor.close();
        return trip;
    }

    public void updateTrip(int tripId, String tripName, byte[] tripIconByteArray){
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", tripName);
        contentValues.put("icon", tripIconByteArray);
        database.update("Trip", contentValues, "id = ?", new String[]{String.valueOf(tripId)});
    }

    public ArrayList<Entry> getEntriesFromTrip(int tripId){
        ArrayList<Entry> entries = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Entry WHERE trip_id = ?", new String[]{String.valueOf(tripId)});

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                MyCustomDate date = new MyCustomDate(cursor.getString(cursor.getColumnIndex("date")));
                String content = cursor.getString(cursor.getColumnIndex("text"));
                Entry entry = new Entry(id, tripId, date, content);
                entries.add(entry);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return entries;
    }

    public void addEntryToTrip(int tripId, MyCustomDate entryDate, String entryContent){
        ContentValues contentValues = new ContentValues();

        contentValues.put("trip_id", tripId);
        contentValues.put("date", entryDate.toString());
        contentValues.put("text", entryContent);
        database.insert("Entry", null, contentValues);

    }

    public Entry getEntryById(int entryId){
        Cursor cursor = database.rawQuery("SELECT * FROM Entry WHERE id = ?", new String[]{String.valueOf(entryId)});

        Entry entry = null;
        if(cursor.moveToNext()){
            int tripId = cursor.getInt(cursor.getColumnIndex("trip_id"));
            MyCustomDate entryDate = new MyCustomDate(cursor.getString(cursor.getColumnIndex("date")));
            String entryContent = cursor.getString(cursor.getColumnIndex("text"));
            entry = new Entry(entryId, tripId, entryDate, entryContent);
        }
        cursor.close();
        return entry;
    }

    public void updateEntry(int entryId, MyCustomDate entryDate, String entryContent){
        ContentValues contentValues = new ContentValues();

        contentValues.put("date", entryDate.toString());
        contentValues.put("text", entryContent);
        database.update("Entry", contentValues, "id = ?", new String[]{String.valueOf(entryId)});
        Cursor cursor = database.rawQuery("SELECT * FROM Entry WHERE id = ?", new String[]{String.valueOf(entryId)});

        Entry entry = null;
        if(cursor.moveToNext()){
            int tripId = cursor.getInt(cursor.getColumnIndex("trip_id"));
            MyCustomDate bla = new MyCustomDate(cursor.getString(cursor.getColumnIndex("date")));
            String foo = cursor.getString(cursor.getColumnIndex("text"));
            entry = new Entry(entryId, tripId, bla, foo);
            Log.d("ENTRY" , entry.toString());
        }
        cursor.close();
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
                cursor.moveToNext();
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
                MyCustomDate startDate = new MyCustomDate(cursor.getString(cursor.getColumnIndex("start_date")));
                MyCustomDate endDate = new MyCustomDate(cursor.getString(cursor.getColumnIndex("end_date")));
                Bitmap tripIconBitmap = BitmapFactory.decodeByteArray(tripIconByteArray, 0, tripIconByteArray.length);
                Trip trip = new Trip(tripName, startDate, endDate, tripId, tripIconBitmap);
                matchingTrips.add(trip);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return matchingTrips;
    }


}
