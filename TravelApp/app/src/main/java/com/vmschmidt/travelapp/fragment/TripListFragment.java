package com.vmschmidt.travelapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.adapter.TripAdapter;
import com.vmschmidt.travelapp.dataprovider.TripProvider;
import com.vmschmidt.travelapp.model.Trip;

import java.util.ArrayList;

public class TripListFragment extends Fragment {

    private ArrayList<Integer> yearsTest;
    private TripAdapter tripAdapter;
    private ArrayList<Trip> trips;
    private RecyclerView tripList;

    public TripListFragment(){
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

        tripList = view.findViewById(R.id.trip_list);
        tripList.setLayoutManager(new LinearLayoutManager(getContext()));
        Spinner yearSelectionSpinner = view.findViewById(R.id.spinner_year_selection);
        Spinner orderBySpinner = view.findViewById(R.id.spinner_order_by);

        setHasOptionsMenu(true);

        trips = TripProvider.getInstance().getAllTrips();

        tripAdapter = new TripAdapter(TripProvider.getInstance().sortTrips(trips, TripProvider.SortingMethod.values()[0]));
        tripList.setAdapter(tripAdapter);

        ArrayList<String> orderByOptions = new ArrayList<>();

        for(TripProvider.SortingMethod sortingMethod : TripProvider.SortingMethod.values()){
            orderByOptions.add(TripProvider.getSortingMethodString(inflater.getContext(), sortingMethod));
        }

        ArrayAdapter<String> orderByAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, orderByOptions);
        orderByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(orderByAdapter);
        orderBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tripAdapter = new TripAdapter(TripProvider.getInstance().sortTrips(trips, TripProvider.SortingMethod.values()[i]));
                tripList.setAdapter(tripAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.custom_menu, menu);
        MenuItem addTripOption = menu.add(R.string.option_add_trip);
        addTripOption.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        addTripOption.setIcon(R.drawable.ic_baseline_add_24);
        addTripOption.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                NavHostFragment.findNavController(TripListFragment.this).navigate(TripListFragmentDirections.actionTripListFragmentToCountryListFragment());
                return false;
            }
        });
    }
}
