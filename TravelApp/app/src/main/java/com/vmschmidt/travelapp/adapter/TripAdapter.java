package com.vmschmidt.travelapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.model.Trip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private List<Trip> trips;
    private OnTripListener onTripListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tripTitle;
        public TextView startDate;
        public TextView endDate;
        public ImageView tripIcon;
        public OnTripListener onTripListener;

        public ViewHolder(@NonNull View v, OnTripListener onTripListener) {
            super(v);
            tripTitle = v.findViewById(R.id.trip_title);
            startDate = v.findViewById(R.id.trip_start_date);
            endDate = v.findViewById(R.id.trip_end_date);
            tripIcon = v.findViewById(R.id.trip_item_icon);
            this.onTripListener = onTripListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTripListener.onTripClick(getAdapterPosition());
        }

        public Context getContext(){
            return itemView.getContext();
        }
    }

    public TripAdapter(List<Trip> trips, OnTripListener onTripListener){
        this.trips = trips;
        this.onTripListener = onTripListener;
    }

    @NonNull
    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.trip_cardview_item, parent, false);
        return new ViewHolder(v, onTripListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Trip trip = trips.get(position);

        holder.tripTitle.setText(trip.getTitle());
        holder.startDate.setText(R.string.start_date);
        holder.startDate.append(" " + trip.getStartDate().toString());
        holder.endDate.setText(R.string.end_date);
        holder.endDate.append(" " + trip.getEndDate().toString());
        Bitmap tripIcon = trip.getIcon();
        if(tripIcon == null){
            holder.tripIcon.setImageBitmap(BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.standard_icon));
        }else{
            holder.tripIcon.setImageBitmap(trip.getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public interface OnTripListener{
        void onTripClick(int position);
    }

    public void setTrips(List<Trip> trips){
        this.trips = trips;
    }
}
