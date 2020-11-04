package com.vmschmidt.travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.model.Entry;

import java.util.List;

public class SimpleEntryAdapter extends ArrayAdapter<Entry> {

    private LayoutInflater inflater;

    public SimpleEntryAdapter(@NonNull Context context, @NonNull List<Entry> objects) {
        super(context, R.layout.simple_entry_list_item, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.simple_entry_list_item, parent, false);
        }

        TextView textViewEntryDate = convertView.findViewById(R.id.tvEntryDate);
        Entry entry = getItem(position);
        textViewEntryDate.setText(entry.getDate().toString());

        return convertView;
    }
}
