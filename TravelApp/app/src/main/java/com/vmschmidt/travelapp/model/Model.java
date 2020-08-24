package com.vmschmidt.travelapp.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vmschmidt.travelapp.database.MyCustomOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        return countries;
    }


}
