package com.vmschmidt.travelapp.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vmschmidt.travelapp.MainActivity;
import com.vmschmidt.travelapp.R;
import com.vmschmidt.travelapp.adapter.SimpleCountryItemAdapter;
import com.vmschmidt.travelapp.dataprovider.CountryProvider;
import com.vmschmidt.travelapp.dataprovider.TripProvider;
import com.vmschmidt.travelapp.model.Country;
import com.vmschmidt.travelapp.model.Model;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class CreateTripFormFragment extends Fragment {

    private static final int STORAGE_PERMISSION_CODE = 1;
    private static final int GET_PICTURE_CONTENT_CODE = 2;

    private ListView selectedCountriesList;
    private EditText tripTitleEditText;
    private ImageView imageViewTripIcon;
    private ArrayList<Country> selectedCountries;

    public CreateTripFormFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_trip_form, container, false);

        selectedCountriesList = view.findViewById(R.id.selected_countries_list_view);
        tripTitleEditText = view.findViewById(R.id.edit_text_trip_title);
        imageViewTripIcon = view.findViewById(R.id.iv_trip_icon);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.standard_icon)
                .override(150, 150)
                .into(imageViewTripIcon);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int[] selectedIndexes = CreateTripFormFragmentArgs.fromBundle(getArguments()).getSelectedIndexes();
        selectedCountries = Model.getInstance().getCountriesFromIndexes(selectedIndexes);

        selectedCountriesList.setAdapter(new SimpleCountryItemAdapter(getContext(), selectedCountries));
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

                if(tripTitleEditText.getText().length() == 0){
                    Toast.makeText(getContext(), R.string.toast_insert_trip_title, Toast.LENGTH_SHORT).show();
                }else{
                    Model.getInstance().addTrip(tripTitleEditText.getText().toString(), selectedCountries, imageToByteArray(imageViewTripIcon));
                    NavHostFragment.findNavController(CreateTripFormFragment.this)
                            .navigate(CreateTripFormFragmentDirections.actionCreateTripFormFragmentToTripListFragment());
                }

                return true;
            }
        });
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