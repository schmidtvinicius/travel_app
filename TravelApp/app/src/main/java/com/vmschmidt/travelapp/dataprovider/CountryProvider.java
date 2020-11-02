package com.vmschmidt.travelapp.dataprovider;

import android.content.Context;
import android.util.Log;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.model.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class CountryProvider {

    private static CountryProvider countryProvider;

    private HashMap<String, Country> countries;

    private CountryProvider(Context context){
        init(context);
    }

    private void init(Context context){
        countries = new HashMap<>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.countrylist);
        Scanner scanner = new Scanner(inputStream);

        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        try{
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Country country = new Country(jsonObject);
                countries.put(country.getCode(), country);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static CountryProvider getInstance(Context context){
        if(countryProvider == null){
            countryProvider = new CountryProvider(context);
            Log.d("Test", "Populating list");
        }
        return countryProvider;
    }

    public ArrayList<Country> getCountries(){
        Collection<Country> countries = this.countries.values();
        ArrayList<Country> countryArrayList = new ArrayList<>(countries);
        Collections.sort(countryArrayList, new Comparator<Country>() {
            @Override
            public int compare(Country c1, Country c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        return countryArrayList;
    }

    public ArrayList<String> getCountriesOnIndexes(int[] selectedIndexes){
        ArrayList<String> matchingCountries = new ArrayList<>();
        ArrayList<Country> countries = getCountries();
        for(int index : selectedIndexes){
            matchingCountries.add(countries.get(index).getName());
        }
        return matchingCountries;
    }
}
