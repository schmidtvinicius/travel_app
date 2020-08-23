package com.vmschmidt.travelapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.dataprovider.CountryProvider;
import com.vmschmidt.travelapp.dataprovider.TripProvider;

import java.util.ArrayList;

public class CreateTripFormFragment extends Fragment {

    private ListView selectedCountriesList;
    private EditText tripTitleEditText;
    private ArrayList<String> selectedCountries;

    public CreateTripFormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_trip_form, container, false);

        selectedCountriesList = view.findViewById(R.id.selected_countries_list_view);
        tripTitleEditText = view.findViewById(R.id.edit_text_trip_title);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int[] selectedIndexes = CreateTripFormFragmentArgs.fromBundle(getArguments()).getSelectedIndexes();
        selectedCountries = CountryProvider.getInstance(getContext()).getCountriesOnIndexes(selectedIndexes);

        selectedCountriesList.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, selectedCountries));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.custom_menu, menu);
        MenuItem confirmOption = menu.add(R.string.option_confirm);
        confirmOption.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        confirmOption.setIcon(R.drawable.ic_baseline_check_24);
        confirmOption.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if(tripTitleEditText.getText().length() == 0){
                    Toast.makeText(getContext(), R.string.toast_insert_trip_title, Toast.LENGTH_SHORT).show();
                }else{
                    TripProvider.getInstance().addTrip(tripTitleEditText.getText().toString(), selectedCountries);
                    NavHostFragment.findNavController(CreateTripFormFragment.this)
                            .navigate(CreateTripFormFragmentDirections.actionCreateTripFormFragmentToTripListFragment());
                }

                return true;
            }
        });
    }
}