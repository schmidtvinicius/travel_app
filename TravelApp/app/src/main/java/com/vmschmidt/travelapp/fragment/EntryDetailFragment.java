package com.vmschmidt.travelapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.model.Entry;
import com.vmschmidt.travelapp.model.Model;
import com.vmschmidt.travelapp.model.Trip;
import com.vmschmidt.travelapp.support.MyCustomDate;

public class EntryDetailFragment extends Fragment {

    private EditText editTextEntryDate;
    private EditText editTextEntryContent;
    private int entryId;
    private int tripId;
    private Entry entry;
    private Trip trip;

    public EntryDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entry_detail, container, false);
        editTextEntryDate = view.findViewById(R.id.editTextEntryDate);
        editTextEntryContent = view.findViewById(R.id.editTextEntryContent);
        entryId = EntryDetailFragmentArgs.fromBundle(getArguments()).getEntryId();
        tripId = EntryDetailFragmentArgs.fromBundle(getArguments()).getTripId();
        entry = Model.getInstance().getEntryById(entryId);
        trip = Model.getInstance().getTripById(tripId);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editTextEntryDate.setText(entry.getDate().toString());
        editTextEntryContent.setText(entry.getContent());
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

                if(editTextEntryDate.getText().length() == 0 || editTextEntryContent.getText().length() == 0){
                    Toast.makeText(getContext(), R.string.toast_insert_entry_data, Toast.LENGTH_SHORT).show();
                }else if(!editTextEntryDate.getText().toString().matches("^((0[1-9])[-]|([1-2][0-9])[-]|(3[0-1])[-])((0[1-9])[-]|(1[0-2])[-])((19[7-9][0-9])|(20)[0-9]{2})$")){
                    Toast.makeText(getContext(), R.string.toast_invalid_entry_date, Toast.LENGTH_LONG).show();
                }else if(editTextEntryDate.getText().toString().compareTo(trip.getEndDate().toString()) > 0
                        || editTextEntryDate.getText().toString().compareTo(trip.getStartDate().toString()) < 0){
                    Toast.makeText(getContext(), R.string.toast_entry_date_outside_range, Toast.LENGTH_LONG).show();
                }else{
                    MyCustomDate entryDate = new MyCustomDate(editTextEntryDate.getText().toString());
                    String entryContent = editTextEntryContent.getText().toString();
                    Model.getInstance().updateEntry(entryId, entryDate, entryContent);
                    NavHostFragment.findNavController(EntryDetailFragment.this)
                            .navigate(EntryDetailFragmentDirections.actionEntryDetailFragmentToEntryListFragment(tripId));
                }
                return true;
            }
        });
    }
}