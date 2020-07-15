package com.vmschmidt.travelapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.RecyclerViewAdapter;
import com.vmschmidt.travelapp.dataprovider.CountryProvider;

public class CountryListFragment extends Fragment implements RecyclerViewAdapter.OnCountryListener {

    private RecyclerViewAdapter adapter;

    public CountryListFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view  = inflater.inflate(R.layout.country_list_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.country_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewAdapter(CountryProvider.getInstance(view.getContext()).getCountries(), this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCountryClick(int position) {
        adapter.updateCheckedCountry(position);
    }
}
