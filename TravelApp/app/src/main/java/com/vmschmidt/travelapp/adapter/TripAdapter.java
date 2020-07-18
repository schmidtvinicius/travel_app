package com.vmschmidt.travelapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.model.Trip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private List<Trip> trips;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tripTitle;
        public TextView startDate;
        public TextView endDate;
        public ImageView tripPreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tripTitle = itemView.findViewById(R.id.trip_title);
            startDate = itemView.findViewById(R.id.trip_start_date);
            endDate = itemView.findViewById(R.id.trip_end_date);
            tripPreview = itemView.findViewById(R.id.trip_item_preview);
        }
    }

    public TripAdapter(List<Trip> trips){
        this.trips = trips;
    }

    @NonNull
    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.trip_cardview_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Trip trip = trips.get(position);

        holder.tripTitle.setText(trip.getTitle());
        holder.startDate.setText(R.string.start_date_from);
        holder.startDate.append(" " + trip.getStartDate());
        holder.endDate.setText(R.string.end_date_to);
        holder.endDate.append(" " + trip.getEndDate());
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
