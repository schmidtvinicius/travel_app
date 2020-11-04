package com.vmschmidt.travelapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.adapter.CountryAdapter;
import com.vmschmidt.travelapp.model.Model;

public class CountryListFragment extends Fragment implements CountryAdapter.OnCountryListener {

    public static final String COUNTRY_LIST_FRAGMENT = "Country_list_fragment";

    private CountryAdapter adapter;

    public CountryListFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view  = inflater.inflate(R.layout.country_selection_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.country_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setHasOptionsMenu(true);

        adapter = new CountryAdapter(Model.getInstance().getCountries(), this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCountryClick(int position) {
        adapter.updateCheckedCountry(position);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.custom_menu, menu);
        MenuItem confirmCountrySelectionOption = menu.add(R.string.option_confirm);
        confirmCountrySelectionOption.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        confirmCountrySelectionOption.setIcon(R.drawable.ic_baseline_check_24);
        confirmCountrySelectionOption.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(adapter.hasSelectedCountries()){
                    NavHostFragment.findNavController(CountryListFragment.this)
                            .navigate(CountryListFragmentDirections.actionCountryListFragmentToCreateTripFormFragment(adapter.getSelectedIndexes()));
                }else{
                    Toast.makeText(getContext(), R.string.toast_select_country, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }
}
