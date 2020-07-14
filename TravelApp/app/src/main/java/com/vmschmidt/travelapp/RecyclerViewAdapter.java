package com.vmschmidt.travelapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vmschmidt.travelapp.dataprovider.FlagProvider;
import com.vmschmidt.travelapp.model.Country;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Country> countries;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView countyName;
        public ImageView countryFlag;
        public ViewHolder(View v) {
            super(v);
            countyName = v.findViewById(R.id.tv_country_name);
            countryFlag = v.findViewById(R.id.img_country_flag);
        }

        public Context getContext(){
            return itemView.getContext();
        }
    }

    public RecyclerViewAdapter(List<Country> countries){
        this.countries = countries;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.country_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
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

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return countries.size();
    }

}