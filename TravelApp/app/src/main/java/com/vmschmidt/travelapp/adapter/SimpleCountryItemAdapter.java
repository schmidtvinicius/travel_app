package com.vmschmidt.travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.dataprovider.FlagProvider;
import com.vmschmidt.travelapp.model.Country;

import java.util.List;

public class SimpleCountryItemAdapter extends ArrayAdapter<Country> {

    private LayoutInflater inflater;

    public SimpleCountryItemAdapter(@NonNull Context context, @NonNull List<Country> objects) {
        super(context, R.layout.simple_country_list_item, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.simple_country_list_item, parent, false);
        }

        TextView countryName = convertView.findViewById(R.id.tv_simple_country_name);
        ImageView countryFlag = convertView.findViewById(R.id.iv_simple_country_flag);

        Country country = getItem(position);
        FlagProvider.Flag flag = FlagProvider.Flag.valueOf(country.getCode());

        countryName.setText(country.getName());
        countryFlag.setImageDrawable(FlagProvider.getDrawable(convertView.getContext(), flag));

        return convertView;
    }
}
