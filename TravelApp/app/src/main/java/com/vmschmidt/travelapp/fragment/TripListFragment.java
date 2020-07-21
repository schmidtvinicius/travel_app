package com.vmschmidt.travelapp.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.adapter.TripAdapter;
import com.vmschmidt.travelapp.dataprovider.TripProvider;
import com.vmschmidt.travelapp.model.Country;
import com.vmschmidt.travelapp.model.Trip;
import com.vmschmidt.travelapp.support.MyCustomDate;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

public class TripListFragment extends Fragment {

    private String[] orderByOptions;
    private ArrayList<Integer> yearsTest;
    private TripAdapter tripAdapter;

    public TripListFragment(){
        orderByOptions = new String[] {"Newest", "Oldest", "Alphabetical"};
        yearsTest = new ArrayList<>();
        yearsTest.add(2000);
        yearsTest.add(2001);
        yearsTest.add(2002);
        yearsTest.add(2003);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.trips_list_fragment, container, false);

        RecyclerView tripList = view.findViewById(R.id.trip_list);
        tripList.setLayoutManager(new LinearLayoutManager(getContext()));
        Spinner yearSelectionSpinner = view.findViewById(R.id.spinner_year_selection);
        Spinner orderBySpinner = view.findViewById(R.id.spinner_order_by);

        yearSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "Bla", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tripAdapter = new TripAdapter(TripProvider.getInstance().getAllTrips());
        tripList.setAdapter(tripAdapter);

        ArrayList<String> orderByOptions = new ArrayList<>(Arrays.asList(this.orderByOptions));
        ArrayAdapter<String> orderByAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, orderByOptions);
        orderByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(orderByAdapter);

        ArrayAdapter<Integer> yearsTestAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, yearsTest);
        yearsTestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSelectionSpinner.setAdapter(yearsTestAdapter);
        yearSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateSelectedYear(yearsTest.get(i));
                tripAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    public void updateSelectedYear(int year){
        ArrayList<Trip> matchingTrips = TripProvider.getInstance().getTripsFromYear(year);
        tripAdapter.setTrips(matchingTrips);
    }
}
