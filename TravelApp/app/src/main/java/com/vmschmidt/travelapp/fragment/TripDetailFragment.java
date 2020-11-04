package com.vmschmidt.travelapp.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.adapter.SimpleCountryItemAdapter;
import com.vmschmidt.travelapp.model.Model;
import com.vmschmidt.travelapp.model.Trip;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

public class TripDetailFragment extends Fragment {

    private static final int STORAGE_PERMISSION_CODE = 1;
    private static final int GET_PICTURE_CONTENT_CODE = 2;

    private ListView countriesListView;
    private EditText tripNameEditText;
    private TextView tripStartDateTextView;
    private TextView tripEndDateTextView;
    private TextView entryListTitle;
    private ImageView imageViewTripIcon;
    private Trip trip;
    private int tripId;

    public TripDetailFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trip_detail, container, false);

        tripId = TripDetailFragmentArgs.fromBundle(getArguments()).getTripId();

        trip = Model.getInstance().getTripById(tripId);

        Log.d("TRIP", trip.toString());

        countriesListView = view.findViewById(R.id.countriesListView);
        tripNameEditText = view.findViewById(R.id.editTextTripName);
        tripStartDateTextView = view.findViewById(R.id.tvTripStartDate);
        tripEndDateTextView = view.findViewById(R.id.tvTripEndDate);
        entryListTitle = view.findViewById(R.id.tvEntriesTitle);
        entryListTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TripDetailFragment.this)
                        .navigate(TripDetailFragmentDirections.actionTripDetailFragmentToEntryListFragment(tripId));
            }
        });

        imageViewTripIcon = view.findViewById(R.id.ivTripIcon);
        imageViewTripIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
                    intent.setType("image/*");
                    startActivityForResult(intent, GET_PICTURE_CONTENT_CODE);
                }else {
                    requestStoragePermission();
                }
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        countriesListView.setAdapter(new SimpleCountryItemAdapter(getContext(), trip.getCountries()));
        tripNameEditText.setText(trip.getTitle());
        tripStartDateTextView.setText(R.string.start_date);
        tripStartDateTextView.append(" " + trip.getStartDate().toString());
        tripEndDateTextView.setText(R.string.end_date);
        tripEndDateTextView.append(" " + trip.getEndDate().toString());
        Glide.with(this)
                .asBitmap()
                .load(trip.getIcon())
                .override(150, 150)
                .into(imageViewTripIcon);
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
                if(tripNameEditText.getText().length() == 0 || tripStartDateTextView.getText().length() == 0 || tripEndDateTextView.getText().length() == 0){
                    Toast.makeText(getContext(), R.string.toast_insert_trip_title, Toast.LENGTH_SHORT).show();
                }else{
                    String tripName = tripNameEditText.getText().toString();
                    byte[] imageByteArray = imageToByteArray(imageViewTripIcon);
                    Model.getInstance().updateTrip(trip.getId(), tripName, imageByteArray);
                    NavHostFragment.findNavController(TripDetailFragment.this)
                            .navigate(TripDetailFragmentDirections.actionTripDetailFragmentToTripListFragment());
                }
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == GET_PICTURE_CONTENT_CODE && resultCode == RESULT_OK){
            Uri imageData = data.getData();
            Glide.with(this)
                    .asBitmap()
                    .load(imageData)
                    .override(150, 150)
                    .into(imageViewTripIcon);

        }
    }

    private byte[] imageToByteArray(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 25, stream);
        return stream.toByteArray();
    }

    public void requestStoragePermission(){
        if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.permission_needed_title)
                    .setMessage(R.string.permission_explanation)
                    .setPositiveButton(R.string.option_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton(R.string.option_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        }else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }
}
