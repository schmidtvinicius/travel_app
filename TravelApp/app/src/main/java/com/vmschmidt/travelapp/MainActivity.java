package com.vmschmidt.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vmschmidt.travelapp.dataprovider.CountryProvider;
import com.vmschmidt.travelapp.fragment.CountryListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CountryListFragment countryListFragment = new CountryListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list_container, countryListFragment)
                .commit();
    }
}