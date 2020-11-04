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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.adapter.SimpleEntryAdapter;
import com.vmschmidt.travelapp.model.Entry;
import com.vmschmidt.travelapp.model.Model;

import java.util.ArrayList;

public class EntryListFragment extends Fragment {

    private ListView listViewEntryList;
    private int tripId;
    private ArrayList<Entry> entries;

    public EntryListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entry_list, container, false);
        tripId = EntryListFragmentArgs.fromBundle(getArguments()).getTripId();
        entries = Model.getInstance().getEntriesFromTrip(tripId);
        listViewEntryList = view.findViewById(R.id.lvEntryList);
        listViewEntryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Entry entry = entries.get(i);
                NavHostFragment.findNavController(EntryListFragment.this)
                        .navigate(EntryListFragmentDirections.actionEntryListFragmentToEntryDetailFragment(entry.getId(), tripId));
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listViewEntryList.setAdapter(new SimpleEntryAdapter(getContext(), entries));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.custom_menu, menu);
        MenuItem addOption = menu.add(R.string.option_add);
        addOption.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        addOption.setIcon(R.drawable.ic_baseline_add_24);
        addOption.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                NavHostFragment.findNavController(EntryListFragment.this)
                        .navigate(EntryListFragmentDirections.actionEntryListFragmentToCreateEntryFragment(tripId));
                return true;
            }
        });

    }
}