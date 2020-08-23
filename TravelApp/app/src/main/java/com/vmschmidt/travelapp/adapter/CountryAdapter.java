package com.vmschmidt.travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.dataprovider.FlagProvider;
import com.vmschmidt.travelapp.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<Country> countries;
    private static boolean[] checkedCountries;
    private OnCountryListener onCountryListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView countyName;
        public ImageView countryFlag;
        public CheckBox checkBox;
        public OnCountryListener onCountryListener;
        public ViewHolder(View v, OnCountryListener onCountryListener) {
            super(v);
            countyName = v.findViewById(R.id.tv_country_name);
            countryFlag = v.findViewById(R.id.img_country_flag);
            checkBox = v.findViewById(R.id.country_checkBox);
            this.onCountryListener = onCountryListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCountryListener.onCountryClick(getAdapterPosition());
            checkBox.setChecked(!checkBox.isChecked());
        }

        public Context getContext(){
            return itemView.getContext();
        }
    }

    public CountryAdapter(List<Country> countries, OnCountryListener onCountryListener){
        this.countries = countries;
        this.onCountryListener = onCountryListener;
        this.checkedCountries = new boolean[countries.size()];
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.country_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v, onCountryListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Country country = countries.get(position);

        holder.countyName.setText(country.getName());
        FlagProvider.Flag flag = FlagProvider.Flag.valueOf(country.getCode());
        holder.countryFlag.setImageDrawable(FlagProvider.getDrawable(holder.getContext(), flag));
        holder.checkBox.setChecked(checkedCountries[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return countries.size();
    }

    public interface OnCountryListener {
        void onCountryClick(int position);
    }

    public void updateCheckedCountry(int position){
        checkedCountries[position] = !checkedCountries[position];
    }

    public boolean hasSelectedCountries(){
        for(boolean b : checkedCountries){
            if(b){
                return true;
            }
        }
        return false;
    }

    public static int[] getSelectedIndexes(){

        ArrayList<Integer> selectedIndexes = new ArrayList<>();

        for(int i = 0; i < checkedCountries.length; i++) {
            if (checkedCountries[i]) {
                selectedIndexes.add(i);
            }
        }

        int[] selectedIndexesArray = new int[selectedIndexes.size()];

        for(int i = 0; i < selectedIndexesArray.length; i++){
            selectedIndexesArray[i] = selectedIndexes.get(i);
        }

        return selectedIndexesArray;
    }

}